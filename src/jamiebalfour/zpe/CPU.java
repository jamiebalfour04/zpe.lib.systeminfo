package jamiebalfour.zpe;

import jamiebalfour.generic.JBBinarySearchTree;
import jamiebalfour.zpe.core.YASSByteCodes;
import jamiebalfour.zpe.core.ZPEObject;
import jamiebalfour.zpe.core.ZPEStructure;
import jamiebalfour.zpe.interfaces.ZPEObjectNativeMethod;
import jamiebalfour.zpe.interfaces.ZPEType;
import jamiebalfour.zpe.types.ZPEList;
import jamiebalfour.zpe.types.ZPENumber;
import jamiebalfour.zpe.types.ZPEString;
import oshi.hardware.CentralProcessor;

public class CPU extends ZPEStructure {

  CentralProcessor cpu;

  public CPU(ZPESystemInfoObject o, oshi.hardware.CentralProcessor cpu) {
    super(o.zpe, o, "CPU");

    addNativeMethod("get_identifier", new get_identifier_Command());
    addNativeMethod("get_vendor", new get_vendor_Command());
    addNativeMethod("get_family", new get_family_Command());
    addNativeMethod("get_model", new get_model_Command());
    addNativeMethod("get_stepping", new get_stepping_Command());
    addNativeMethod("get_physical_core_count", new get_physical_core_count_Command());
    addNativeMethod("get_logical_core_count", new get_logical_core_count_Command());
    addNativeMethod("get_current_frequencies", new get_current_frequencies_Command());

    this.cpu = cpu;
  }

  public class get_identifier_Command implements ZPEObjectNativeMethod {

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
      return "get_identifier";
    }

    @Override
    public byte[] returnTypes() {
      return new byte[]{YASSByteCodes.STRING_TYPE};
    }


  }

  public class get_vendor_Command implements ZPEObjectNativeMethod {

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
      return "get_vendor";
    }

    @Override
    public byte[] returnTypes() {
      return new byte[]{YASSByteCodes.STRING_TYPE};
    }

  }

  public class get_family_Command implements ZPEObjectNativeMethod {
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
      return "get_family";
    }

    @Override
    public byte[] returnTypes() {
      return new byte[]{YASSByteCodes.STRING_TYPE};
    }
  }

  public class get_model_Command implements ZPEObjectNativeMethod {
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
      return "get_model";
    }

    @Override
    public byte[] returnTypes() {
      return new byte[]{YASSByteCodes.STRING_TYPE};
    }
  }

  public class get_stepping_Command implements ZPEObjectNativeMethod {
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
      return "get_stepping";
    }

    @Override
    public byte[] returnTypes() {
      return new byte[]{YASSByteCodes.STRING_TYPE};
    }
  }

  public class get_physical_core_count_Command implements ZPEObjectNativeMethod {
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
      return "get_physical_core_count";
    }

    @Override
    public byte[] returnTypes() {
      return new byte[]{YASSByteCodes.NUMBER_TYPE};
    }
  }

  public class get_logical_core_count_Command implements ZPEObjectNativeMethod {
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
      return "get_logical_core_count";
    }

    @Override
    public byte[] returnTypes() {
      return new byte[]{YASSByteCodes.NUMBER_TYPE};
    }
  }

  public class get_current_frequencies_Command implements ZPEObjectNativeMethod {
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
      return "get_current_frequencies";
    }

    @Override
    public byte[] returnTypes() {
      return new byte[]{YASSByteCodes.LIST_TYPE};
    }
  }
}
