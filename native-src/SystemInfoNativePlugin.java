import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.UnmanagedMemory;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CCharPointerPointer;
import org.graalvm.word.WordFactory;
import oshi.ffm.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;

import java.nio.charset.StandardCharsets;
import java.util.List;

/** GraalVM shared-library adapter for ZPEX. It deliberately has no dependency on ZPE. */
public final class SystemInfoNativePlugin {
  private static final ObjectHandles HANDLES = ObjectHandles.getGlobal();
  private static CCharPointer descriptor = WordFactory.nullPointer();

  private static final String DESCRIPTOR = "{"
      + "\"abiVersion\":1,\"name\":\"libSystemInfo\",\"version\":\"1.0\",\"functions\":[],\"objects\":["
      + "{\"name\":\"SystemInfo\",\"constructorParameters\":[],\"properties\":[],\"methods\":["
      + method("get_cpu", "{\"type\":\"object\",\"objectType\":\"CPU\"}") + ","
      + method("get_system_manufacturer", "\"string\"") + ","
      + method("get_system_model", "\"string\"") + ","
      + method("get_baseboard_manufacturer", "\"string\"") + ","
      + method("get_baseboard_model", "\"string\"") + ","
      + method("get_bios_manufacturer", "\"string\"") + ","
      + method("get_bios_version", "\"string\"") + ","
      + method("get_bios_release_date", "\"string\"") + ","
      + method("get_sensors", "\"string\"") + ","
      + method("get_graphics_cards", "\"list\"") + "]},"
      + "{\"name\":\"CPU\",\"constructorParameters\":[],\"properties\":[],\"methods\":["
      + method("get_identifier", "\"string\"") + ","
      + method("get_vendor", "\"string\"") + ","
      + method("get_family", "\"string\"") + ","
      + method("get_model", "\"string\"") + ","
      + method("get_stepping", "\"string\"") + ","
      + method("get_physical_core_count", "\"number\"") + ","
      + method("get_logical_core_count", "\"number\"") + ","
      + method("get_current_frequencies", "\"list\"") + "]},"
      + "{\"name\":\"GraphicsCard\",\"constructorParameters\":[],\"properties\":[],\"methods\":["
      + method("get_name", "\"string\"") + ","
      + method("get_vendor", "\"string\"") + ","
      + method("get_vram", "\"number\"") + ","
      + method("get_device_id", "\"string\"") + "]}]}";

  private static final class SystemInfoObject {
    final HardwareAbstractionLayer hardware = new SystemInfo().getHardware();
    final CentralProcessor processor = hardware.getProcessor();
    final ComputerSystem computer = hardware.getComputerSystem();
  }

  public static void main(String[] arguments) { }

  private static String method(String name, String returnType) {
    return "{\"name\":\"" + name + "\",\"parameters\":[],\"returnType\":" + returnType + "}";
  }

  private static CCharPointer cString(String value) {
    byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
    CCharPointer output = UnmanagedMemory.malloc(bytes.length + 1);
    for (int i = 0; i < bytes.length; i++) output.write(i, bytes[i]);
    output.write(bytes.length, (byte) 0);
    return output;
  }

  private static String javaString(CCharPointer value) {
    if (value.isNull()) return "";
    int length = 0;
    while (value.read(length) != 0) length++;
    byte[] bytes = new byte[length];
    for (int i = 0; i < length; i++) bytes[i] = value.read(i);
    return new String(bytes, StandardCharsets.UTF_8);
  }

  private static String escape(String value) {
    if (value == null) return "";
    return value.replace("\\", "\\\\").replace("\"", "\\\"")
        .replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
  }

  private static CCharPointer value(String type, String jsonValue) {
    return cString("{\"kind\":\"value\",\"value\":{\"type\":\"" + type + "\",\"value\":" + jsonValue + "}}");
  }

  private static CCharPointer stringValue(String value) {
    return value("string", "\"" + escape(value) + "\"");
  }

  private static CCharPointer numberValue(long value) {
    return value("number", Long.toString(value));
  }

  private static CCharPointer numberList(long[] values) {
    StringBuilder json = new StringBuilder("{\"kind\":\"value\",\"value\":{\"type\":\"list\",\"value\":[");
    for (int i = 0; i < values.length; i++) {
      if (i > 0) json.append(',');
      json.append("{\"type\":\"number\",\"value\":").append(values[i]).append('}');
    }
    return cString(json.append("]}}").toString());
  }

  private static CCharPointer objectValue(String type, Object object) {
    return cString("{\"kind\":\"object\",\"objectType\":\"" + type + "\",\"handle\":" + retain(object) + "}");
  }

  private static CCharPointer graphicsCardList(List<GraphicsCard> cards) {
    StringBuilder json = new StringBuilder("{\"kind\":\"value\",\"value\":{\"type\":\"list\",\"value\":[");
    for (int i = 0; i < cards.size(); i++) {
      if (i > 0) json.append(',');
      json.append("{\"type\":\"object\",\"objectType\":\"GraphicsCard\",\"handle\":")
          .append(retain(cards.get(i))).append('}');
    }
    return cString(json.append("]}}").toString());
  }


  private static Object object(long handle) {
    return HANDLES.get(WordFactory.pointer(handle));
  }

  private static long retain(Object value) {
    return HANDLES.create(value).rawValue();
  }

  private static void fail(CCharPointerPointer error, Throwable throwable) {
    if (error.isNonNull()) {
      Throwable cause = throwable;
      while (cause.getCause() != null && cause.getCause() != cause) cause = cause.getCause();
      String message = cause.getMessage();
      error.write(cString(cause.getClass().getSimpleName()
          + (message == null || message.isEmpty() ? "" : ": " + message)));
    }
  }

  @CEntryPoint(name = "zpe_graal_plugin_abi_version")
  public static int abiVersion(IsolateThread thread) { return 1; }

  @CEntryPoint(name = "zpe_graal_plugin_descriptor")
  public static CCharPointer descriptor(IsolateThread thread) {
    if (descriptor.isNull()) descriptor = cString(DESCRIPTOR);
    return descriptor;
  }

  @CEntryPoint(name = "zpe_graal_plugin_create")
  public static long create(IsolateThread thread, CCharPointer type, CCharPointer arguments,
                            CCharPointerPointer error) {
    try {
      String name = javaString(type);
      if ("SystemInfo".equals(name)) return retain(new SystemInfoObject());
      throw new IllegalArgumentException("Objects of type '" + name + "' cannot be constructed directly.");
    } catch (Throwable throwable) {
      fail(error, throwable);
      return 0;
    }
  }

  @CEntryPoint(name = "zpe_graal_plugin_invoke")
  public static CCharPointer invoke(IsolateThread thread, long handle, CCharPointer type,
                                    CCharPointer method, CCharPointer arguments,
                                    CCharPointerPointer error) {
    try {
      String objectType = javaString(type);
      String name = javaString(method);
      if ("SystemInfo".equals(objectType)) {
        SystemInfoObject info = (SystemInfoObject) object(handle);
        if ("get_cpu".equals(name)) return objectValue("CPU", info.processor);
        if ("get_system_manufacturer".equals(name)) return stringValue(info.computer.getManufacturer());
        if ("get_system_model".equals(name)) return stringValue(info.computer.getModel());
        if ("get_baseboard_manufacturer".equals(name)) return stringValue(info.computer.getBaseboard().getManufacturer());
        if ("get_baseboard_model".equals(name)) return stringValue(info.computer.getBaseboard().getModel());
        if ("get_bios_manufacturer".equals(name)) return stringValue(info.computer.getFirmware().getManufacturer());
        if ("get_bios_version".equals(name)) return stringValue(info.computer.getFirmware().getVersion());
        if ("get_bios_release_date".equals(name)) return stringValue(info.computer.getFirmware().getReleaseDate());
        if ("get_sensors".equals(name)) return stringValue(info.hardware.getSensors().toString());
        if ("get_graphics_cards".equals(name)) return graphicsCardList(info.hardware.getGraphicsCards());
      } else if ("CPU".equals(objectType)) {
        CentralProcessor processor = (CentralProcessor) object(handle);
        CentralProcessor.ProcessorIdentifier identifier = processor.getProcessorIdentifier();
        if ("get_identifier".equals(name)) return stringValue(identifier.getName());
        if ("get_vendor".equals(name)) return stringValue(identifier.getVendor());
        if ("get_family".equals(name)) return stringValue(identifier.getFamily());
        if ("get_model".equals(name)) return stringValue(identifier.getModel());
        if ("get_stepping".equals(name)) return stringValue(identifier.getStepping());
        if ("get_physical_core_count".equals(name)) return numberValue(processor.getPhysicalProcessorCount());
        if ("get_logical_core_count".equals(name)) return numberValue(processor.getLogicalProcessorCount());
        if ("get_current_frequencies".equals(name)) return numberList(processor.getCurrentFreq());
      } else if ("GraphicsCard".equals(objectType)) {
        GraphicsCard card = (GraphicsCard) object(handle);
        if ("get_name".equals(name)) return stringValue(card.getName());
        if ("get_vendor".equals(name)) return stringValue(card.getVendor());
        if ("get_vram".equals(name)) return numberValue(card.getVRam());
        if ("get_device_id".equals(name)) return stringValue(card.getDeviceId());
      }
      throw new IllegalArgumentException("Unknown method '" + name + "' on " + objectType + ".");
    } catch (Throwable throwable) {
      fail(error, throwable);
      return WordFactory.nullPointer();
    }
  }

  @CEntryPoint(name = "zpe_graal_plugin_invoke_function")
  public static CCharPointer invokeFunction(IsolateThread thread, CCharPointer function,
                                            CCharPointer arguments, CCharPointerPointer error) {
    fail(error, new IllegalArgumentException("This plugin does not expose global functions."));
    return WordFactory.nullPointer();
  }

  @CEntryPoint(name = "zpe_graal_plugin_get_property")
  public static CCharPointer getProperty(IsolateThread thread, long handle, CCharPointer type,
                                         CCharPointer property, CCharPointerPointer error) {
    fail(error, new IllegalArgumentException("System information objects do not expose properties."));
    return WordFactory.nullPointer();
  }

  @CEntryPoint(name = "zpe_graal_plugin_set_property")
  public static int setProperty(IsolateThread thread, long handle, CCharPointer type,
                                CCharPointer property, CCharPointer value, CCharPointerPointer error) {
    fail(error, new IllegalArgumentException("System information objects are read-only."));
    return 1;
  }

  @CEntryPoint(name = "zpe_graal_plugin_destroy")
  public static void destroy(IsolateThread thread, long handle, CCharPointer type) {
    HANDLES.destroy(WordFactory.pointer(handle));
  }

  @CEntryPoint(name = "zpe_graal_plugin_free_string")
  public static void freeString(IsolateThread thread, CCharPointer value) {
    if (value.isNonNull()) UnmanagedMemory.free(value);
  }
}
