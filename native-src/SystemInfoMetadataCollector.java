import oshi.ffm.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;

/** Exercises the native adapter's OSHI surface so Native Image can record FFM calls. */
public final class SystemInfoMetadataCollector {
  private SystemInfoMetadataCollector() { }

  public static void main(String[] arguments) {
    HardwareAbstractionLayer hardware = new SystemInfo().getHardware();
    CentralProcessor processor = hardware.getProcessor();
    CentralProcessor.ProcessorIdentifier identifier = processor.getProcessorIdentifier();
    identifier.getName();
    identifier.getVendor();
    identifier.getFamily();
    identifier.getModel();
    identifier.getStepping();
    processor.getPhysicalProcessorCount();
    processor.getLogicalProcessorCount();
    processor.getCurrentFreq();
    hardware.getComputerSystem().getManufacturer();
    hardware.getComputerSystem().getModel();
    hardware.getComputerSystem().getBaseboard().getManufacturer();
    hardware.getComputerSystem().getBaseboard().getModel();
    hardware.getComputerSystem().getFirmware().getManufacturer();
    hardware.getComputerSystem().getFirmware().getVersion();
    hardware.getComputerSystem().getFirmware().getReleaseDate();
    hardware.getSensors().toString();
    for (GraphicsCard card : hardware.getGraphicsCards()) {
      card.getName();
      card.getVendor();
      card.getVRam();
      card.getDeviceId();
    }
  }
}
