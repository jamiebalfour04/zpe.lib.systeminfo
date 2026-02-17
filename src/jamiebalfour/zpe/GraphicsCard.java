package jamiebalfour.zpe;

import jamiebalfour.generic.JBBinarySearchTree;
import jamiebalfour.zpe.core.ZPEObject;
import jamiebalfour.zpe.core.ZPEStructure;
import jamiebalfour.zpe.interfaces.ZPEObjectNativeMethod;
import jamiebalfour.zpe.interfaces.ZPEType;
import jamiebalfour.zpe.types.ZPEList;
import jamiebalfour.zpe.types.ZPEMap;
import jamiebalfour.zpe.types.ZPENumber;
import jamiebalfour.zpe.types.ZPEString;

import java.util.List;

public class GraphicsCard extends ZPEStructure {

  ZPESystemInfoObject zpe;
  oshi.hardware.GraphicsCard card;

  public GraphicsCard(ZPESystemInfoObject o, oshi.hardware.GraphicsCard card) {
    super(o.zpe, o, "GraphicsCard");

    addNativeMethod("get_name", new get_name_Command());
    addNativeMethod("get_vendor", new get_vendor_Command());
    addNativeMethod("get_vram", new get_vram_Command());
    addNativeMethod("get_device_id", new get_device_id_Command());

    this.card = card;
  }

  public class get_name_Command implements ZPEObjectNativeMethod {
    @Override public String[] getParameterNames() { return new String[]{}; }
    @Override public String[] getParameterTypes() { return new String[]{}; }

    @Override
    public ZPEType MainMethod(JBBinarySearchTree<String, ZPEType> parameters, ZPEObject parent) {
        return new ZPEString(card.getName());
    }

    @Override public int getRequiredPermissionLevel() { return 0; }
    @Override public String getName() { return "get_name"; }
  }

  public class get_vendor_Command implements ZPEObjectNativeMethod {
    @Override public String[] getParameterNames() { return new String[]{}; }
    @Override public String[] getParameterTypes() { return new String[]{}; }

    @Override
    public ZPEType MainMethod(JBBinarySearchTree<String, ZPEType> parameters, ZPEObject parent) {
      return new ZPEString(card.getVendor());
    }

    @Override public int getRequiredPermissionLevel() { return 0; }
    @Override public String getName() { return "get_vendor"; }
  }

  public class get_vram_Command implements ZPEObjectNativeMethod {
    @Override public String[] getParameterNames() { return new String[]{}; }
    @Override public String[] getParameterTypes() { return new String[]{}; }

    @Override
    public ZPEType MainMethod(JBBinarySearchTree<String, ZPEType> parameters, ZPEObject parent) {
      return new ZPENumber(card.getVRam());
    }

    @Override public int getRequiredPermissionLevel() { return 0; }
    @Override public String getName() { return "get_vram"; }
  }

  public class get_device_id_Command implements ZPEObjectNativeMethod {
    @Override public String[] getParameterNames() { return new String[]{}; }
    @Override public String[] getParameterTypes() { return new String[]{}; }

    @Override
    public ZPEType MainMethod(JBBinarySearchTree<String, ZPEType> parameters, ZPEObject parent) {
      return new ZPEString(card.getDeviceId());
    }

    @Override public int getRequiredPermissionLevel() { return 0; }
    @Override public String getName() { return "get_device_id"; }
  }



}
