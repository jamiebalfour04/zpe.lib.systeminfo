package jamiebalfour.zpe;

import jamiebalfour.generic.JBBinarySearchTree;
import jamiebalfour.zpe.core.YASSByteCodes;
import jamiebalfour.zpe.core.ZPEObject;
import jamiebalfour.zpe.core.ZPERuntimeEnvironment;
import jamiebalfour.zpe.core.ZPEStructure;
import jamiebalfour.zpe.interfaces.ZPEObjectNativeMethod;
import jamiebalfour.zpe.interfaces.ZPEPropertyWrapper;
import jamiebalfour.zpe.interfaces.ZPEType;
import jamiebalfour.zpe.types.ZPEList;
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
  ZPERuntimeEnvironment zpe;

  public ZPESystemInfoObject(ZPERuntimeEnvironment z, ZPEPropertyWrapper parent) {
    super(z, parent, "ZPESystemInfo");
    zpe = z;
    System.setProperty("jna.nosys", "true");
    System.setProperty("jna.tmpdir", "/Users/jamiebalfour/tmp");

    si = new SystemInfo();
    hal = si.getHardware();
    cpu = hal.getProcessor();
    cs = hal.getComputerSystem();

    addNativeMethod("get_cpu", new get_cpu_Command());
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

  public class get_cpu_Command implements ZPEObjectNativeMethod {

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
      return new CPU((ZPESystemInfoObject) parent, cpu);
    }

    @Override
    public int getRequiredPermissionLevel() {
      return 0;
    }

    public String getName() {
      return "get_cpu";
    }

    @Override
    public byte[] returnTypes() {
      return new byte[]{YASSByteCodes.OBJECT_TYPE};
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

    @Override
    public byte[] returnTypes() {
      return new byte[]{YASSByteCodes.STRING_TYPE};
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

    @Override
    public byte[] returnTypes() {
      return new byte[]{YASSByteCodes.STRING_TYPE};
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

    @Override
    public byte[] returnTypes() {
      return new byte[]{YASSByteCodes.STRING_TYPE};
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

    @Override
    public byte[] returnTypes() {
      return new byte[]{YASSByteCodes.STRING_TYPE};
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

    @Override
    public byte[] returnTypes() {
      return new byte[]{YASSByteCodes.STRING_TYPE};
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

    @Override
    public byte[] returnTypes() {
      return new byte[]{YASSByteCodes.STRING_TYPE};
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

    @Override
    public byte[] returnTypes() {
      return new byte[]{YASSByteCodes.STRING_TYPE};
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

    @Override
    public byte[] returnTypes() {
      return new byte[]{YASSByteCodes.STRING_TYPE};
    }
  }

  public class get_graphics_cards_Command implements ZPEObjectNativeMethod {
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
      List<GraphicsCard> cards = hal.getGraphicsCards();
      if (cards.isEmpty()) {
        return new ZPEList();
      } else {
        ZPEList output = new ZPEList();
        for (GraphicsCard gc : cards) {
          jamiebalfour.zpe.GraphicsCard c = new jamiebalfour.zpe.GraphicsCard((ZPESystemInfoObject) parent, gc);
          output.add(c);
        }
        return output;
      }

    }

    @Override
    public int getRequiredPermissionLevel() {
      return 0;
    }

    @Override
    public String getName() {
      return "get_graphics_cards";
    }

    @Override
    public byte[] returnTypes() {
      return new byte[]{YASSByteCodes.LIST_TYPE};
    }
  }
}
