package jamiebalfour.zpe;

import jamiebalfour.generic.JBBinarySearchTree;
import jamiebalfour.zpe.core.ZPEObject;
import jamiebalfour.zpe.core.ZPERuntimeEnvironment;
import jamiebalfour.zpe.core.ZPEStructure;
import jamiebalfour.zpe.interfaces.ZPEObjectNativeMethod;
import jamiebalfour.zpe.interfaces.ZPEPropertyWrapper;
import jamiebalfour.zpe.interfaces.ZPEType;
import jamiebalfour.zpe.types.ZPEList;
import jamiebalfour.zpe.types.ZPEMap;
import jamiebalfour.zpe.types.ZPENumber;
import jamiebalfour.zpe.types.ZPEString;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;
import java.util.List;


public class ZPESystemInfoObject extends ZPEStructure {

  SystemInfo si;
  HardwareAbstractionLayer hal;
  CentralProcessor cpu;
  ComputerSystem cs;

  public ZPESystemInfoObject(ZPERuntimeEnvironment z, ZPEPropertyWrapper parent) {
    super(z, parent, "ZPESystemInfo");
    System.setProperty("jna.nosys", "true");
    System.setProperty("jna.tmpdir", "/Users/jamiebalfour/tmp");

    si = new SystemInfo();
    hal = si.getHardware();
    cpu = hal.getProcessor();
    cs = hal.getComputerSystem();

    addNativeMethod("get_cpu_identifier", new get_cpu_identifier_Command());
    addNativeMethod("get_cpu_vendor", new get_cpu_vendor_Command());
    addNativeMethod("get_cpu_family", new get_cpu_family_Command());
    addNativeMethod("get_cpu_model", new get_cpu_model_Command());
    addNativeMethod("get_cpu_stepping", new get_cpu_stepping_Command());
    addNativeMethod("get_cpu_physical_core_count", new get_cpu_physical_core_count_Command());
    addNativeMethod("get_cpu_logical_core_count", new get_cpu_logical_core_count_Command());
    addNativeMethod("get_cpu_current_frequencies", new get_cpu_current_frequencies_Command());
    addNativeMethod("get_system_manufacturer", new get_system_manufacturer_Command());
    addNativeMethod("get_system_model", new get_system_model_Command());
    addNativeMethod("get_baseboard_manufacturer", new get_baseboard_manufacturer_Command());

    addNativeMethod("get_baseboard_model", new get_baseboard_model_Command());
    addNativeMethod("get_bios_manufacturer", new get_bios_manufacturer_Command());
    addNativeMethod("get_bios_version", new get_bios_version_Command());
    addNativeMethod("get_bios_release_date", new get_bios_release_date_Command());

    addNativeMethod("get_sensors", new get_sensors_Command());
    addNativeMethod("get_graphics_cards", new get_graphics_cards_Command());


  }


  public class get_cpu_identifier_Command implements ZPEObjectNativeMethod {

    @Override
    public String[] getParameterNames() {
      return new String[]{};
    }

    @Override
    public String[] getParameterTypes() {
      return new String[]{};
    }

    @Override
    public ZPEType MainMethod(JBBinarySearchTree<String, ZPEType> parameters, ZPEObject parent) {

      return new ZPEString(cpu.getProcessorIdentifier().getName());
    }

    @Override
    public int getRequiredPermissionLevel() {
      return 0;
    }

    public String getName() {
      return "get_cpu_identifier";
    }

  }

  public class get_cpu_vendor_Command implements ZPEObjectNativeMethod {

    @Override
    public String[] getParameterNames() {
      return new String[]{};
    }

    @Override
    public String[] getParameterTypes() {
      return new String[]{};
    }

    @Override
    public ZPEType MainMethod(JBBinarySearchTree<String, ZPEType> parameters, ZPEObject parent) {

      return new ZPEString(cpu.getProcessorIdentifier().getVendor());
    }

    @Override
    public int getRequiredPermissionLevel() {
      return 0;
    }

    public String getName() {
      return "get_cpu_vendor";
    }

  }

  public class get_cpu_family_Command implements ZPEObjectNativeMethod {
    @Override
    public String[] getParameterNames() {
      return new String[]{};
    }

    @Override
    public String[] getParameterTypes() {
      return new String[]{};
    }

    @Override
    public ZPEType MainMethod(JBBinarySearchTree<String, ZPEType> parameters, ZPEObject parent) {
      return new ZPEString(cpu.getProcessorIdentifier().getFamily());
    }

    @Override
    public int getRequiredPermissionLevel() {
      return 0;
    }

    @Override
    public String getName() {
      return "get_cpu_family";
    }
  }

  public class get_cpu_model_Command implements ZPEObjectNativeMethod {
    @Override
    public String[] getParameterNames() {
      return new String[]{};
    }

    @Override
    public String[] getParameterTypes() {
      return new String[]{};
    }

    @Override
    public ZPEType MainMethod(JBBinarySearchTree<String, ZPEType> parameters, ZPEObject parent) {
      return new ZPEString(cpu.getProcessorIdentifier().getModel());
    }

    @Override
    public int getRequiredPermissionLevel() {
      return 0;
    }

    @Override
    public String getName() {
      return "get_cpu_model";
    }
  }

  public class get_cpu_stepping_Command implements ZPEObjectNativeMethod {
    @Override
    public String[] getParameterNames() {
      return new String[]{};
    }

    @Override
    public String[] getParameterTypes() {
      return new String[]{};
    }

    @Override
    public ZPEType MainMethod(JBBinarySearchTree<String, ZPEType> parameters, ZPEObject parent) {
      return new ZPEString(cpu.getProcessorIdentifier().getStepping());
    }

    @Override
    public int getRequiredPermissionLevel() {
      return 0;
    }

    @Override
    public String getName() {
      return "get_cpu_stepping";
    }
  }

  public class get_cpu_physical_core_count_Command implements ZPEObjectNativeMethod {
    @Override
    public String[] getParameterNames() {
      return new String[]{};
    }

    @Override
    public String[] getParameterTypes() {
      return new String[]{};
    }

    @Override
    public ZPEType MainMethod(JBBinarySearchTree<String, ZPEType> parameters, ZPEObject parent) {
      return new ZPENumber(cpu.getPhysicalProcessorCount());
    }

    @Override
    public int getRequiredPermissionLevel() {
      return 0;
    }

    @Override
    public String getName() {
      return "get_cpu_physical_core_count";
    }
  }

  public class get_cpu_logical_core_count_Command implements ZPEObjectNativeMethod {
    @Override
    public String[] getParameterNames() {
      return new String[]{};
    }

    @Override
    public String[] getParameterTypes() {
      return new String[]{};
    }

    @Override
    public ZPEType MainMethod(JBBinarySearchTree<String, ZPEType> parameters, ZPEObject parent) {
      return new ZPENumber(cpu.getLogicalProcessorCount());
    }

    @Override
    public int getRequiredPermissionLevel() {
      return 0;
    }

    @Override
    public String getName() {
      return "get_cpu_logical_core_count";
    }
  }

  public class get_cpu_current_frequencies_Command implements ZPEObjectNativeMethod {
    @Override
    public String[] getParameterNames() {
      return new String[]{};
    }

    @Override
    public String[] getParameterTypes() {
      return new String[]{};
    }

    @Override
    public ZPEType MainMethod(JBBinarySearchTree<String, ZPEType> parameters, ZPEObject parent) {
      long[] freqs = cpu.getCurrentFreq(); // may include zeros depending on OS
      ZPEList output = new ZPEList();
      for (long freq : freqs) {
        output.add(new ZPENumber(freq));
      }
      return output;
    }

    @Override
    public int getRequiredPermissionLevel() {
      return 0;
    }

    @Override
    public String getName() {
      return "get_cpu_current_frequencies";
    }
  }

  public class get_system_manufacturer_Command implements ZPEObjectNativeMethod {
    @Override
    public String[] getParameterNames() {
      return new String[]{};
    }

    @Override
    public String[] getParameterTypes() {
      return new String[]{};
    }

    @Override
    public ZPEType MainMethod(JBBinarySearchTree<String, ZPEType> parameters, ZPEObject parent) {
      return new ZPEString(cs.getManufacturer());
    }

    @Override
    public int getRequiredPermissionLevel() {
      return 0;
    }

    @Override
    public String getName() {
      return "get_system_manufacturer";
    }
  }

  public class get_system_model_Command implements ZPEObjectNativeMethod {
    @Override
    public String[] getParameterNames() {
      return new String[]{};
    }

    @Override
    public String[] getParameterTypes() {
      return new String[]{};
    }

    @Override
    public ZPEType MainMethod(JBBinarySearchTree<String, ZPEType> parameters, ZPEObject parent) {
      return new ZPEString(cs.getModel());
    }

    @Override
    public int getRequiredPermissionLevel() {
      return 0;
    }

    @Override
    public String getName() {
      return "get_system_model";
    }
  }

  public class get_baseboard_manufacturer_Command implements ZPEObjectNativeMethod {
    @Override
    public String[] getParameterNames() {
      return new String[]{};
    }

    @Override
    public String[] getParameterTypes() {
      return new String[]{};
    }

    @Override
    public ZPEType MainMethod(JBBinarySearchTree<String, ZPEType> parameters, ZPEObject parent) {
      return new ZPEString(cs.getBaseboard().getManufacturer());
    }

    @Override
    public int getRequiredPermissionLevel() {
      return 0;
    }

    @Override
    public String getName() {
      return "get_baseboard_manufacturer";
    }
  }

  public class get_baseboard_model_Command implements ZPEObjectNativeMethod {
    @Override
    public String[] getParameterNames() {
      return new String[]{};
    }

    @Override
    public String[] getParameterTypes() {
      return new String[]{};
    }

    @Override
    public ZPEType MainMethod(JBBinarySearchTree<String, ZPEType> parameters, ZPEObject parent) {
      return new ZPEString(cs.getBaseboard().getModel());
    }

    @Override
    public int getRequiredPermissionLevel() {
      return 0;
    }

    @Override
    public String getName() {
      return "get_baseboard_model";
    }
  }

  public class get_bios_manufacturer_Command implements ZPEObjectNativeMethod {
    @Override
    public String[] getParameterNames() {
      return new String[]{};
    }

    @Override
    public String[] getParameterTypes() {
      return new String[]{};
    }

    @Override
    public ZPEType MainMethod(JBBinarySearchTree<String, ZPEType> parameters, ZPEObject parent) {
      return new ZPEString(cs.getFirmware().getManufacturer());
    }

    @Override
    public int getRequiredPermissionLevel() {
      return 0;
    }

    @Override
    public String getName() {
      return "get_bios_manufacturer";
    }
  }

  public class get_bios_version_Command implements ZPEObjectNativeMethod {
    @Override
    public String[] getParameterNames() {
      return new String[]{};
    }

    @Override
    public String[] getParameterTypes() {
      return new String[]{};
    }

    @Override
    public ZPEType MainMethod(JBBinarySearchTree<String, ZPEType> parameters, ZPEObject parent) {
      return new ZPEString(cs.getFirmware().getVersion());
    }

    @Override
    public int getRequiredPermissionLevel() {
      return 0;
    }

    @Override
    public String getName() {
      return "get_bios_version";
    }
  }

  public class get_bios_release_date_Command implements ZPEObjectNativeMethod {
    @Override
    public String[] getParameterNames() {
      return new String[]{};
    }

    @Override
    public String[] getParameterTypes() {
      return new String[]{};
    }

    @Override
    public ZPEType MainMethod(JBBinarySearchTree<String, ZPEType> parameters, ZPEObject parent) {
      return new ZPEString(cs.getFirmware().getReleaseDate());
    }

    @Override
    public int getRequiredPermissionLevel() {
      return 0;
    }

    @Override
    public String getName() {
      return "get_bios_release_date";
    }
  }

  public class get_sensors_Command implements ZPEObjectNativeMethod {
    @Override
    public String[] getParameterNames() {
      return new String[]{};
    }

    @Override
    public String[] getParameterTypes() {
      return new String[]{};
    }

    @Override
    public ZPEType MainMethod(JBBinarySearchTree<String, ZPEType> parameters, ZPEObject parent) {
      // Matches your demo output style
      return new ZPEString(hal.getSensors().toString());
    }

    @Override
    public int getRequiredPermissionLevel() {
      return 0;
    }

    @Override
    public String getName() {
      return "get_sensors";
    }
  }

  public class get_graphics_cards_Command implements ZPEObjectNativeMethod {
    @Override public String[] getParameterNames() { return new String[]{}; }
    @Override public String[] getParameterTypes() { return new String[]{}; }

    @Override
    public ZPEType MainMethod(JBBinarySearchTree<String, ZPEType> parameters, ZPEObject parent) {
      List<GraphicsCard> cards = hal.getGraphicsCards();
      if (cards.isEmpty()) {
        return new ZPEList();
      } else {
        ZPEList output = new ZPEList();
        for (GraphicsCard gc : cards) {
          ZPEMap cardMap = new ZPEMap();
          cardMap.put("name", new ZPEString(gc.getName()));
          cardMap.put("vendor", new ZPEString(gc.getVendor()));
          cardMap.put("vram", new ZPENumber(gc.getVRam()));
          cardMap.put("device_id", new ZPEString(gc.getDeviceId()));
          output.add(cardMap);
        }
        return output;
      }

    }

    @Override public int getRequiredPermissionLevel() { return 0; }
    @Override public String getName() { return "get_graphics_cards"; }
  }
}
