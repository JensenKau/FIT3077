package ninemanmorris.Manager;

import java.util.EnumMap;

import ninemanmorris.Controller.ScreenController;
import ninemanmorris.Enum.PageEnum;

public class ScreenManager {

  private EnumMap<PageEnum, ScreenController> enumToScreenController;

  private EnumMap<PageEnum, String> enumToScreenFxml;

  private static ScreenManager instance;

  private ScreenManager() {
    enumToScreenController = new EnumMap<PageEnum, ScreenController>(PageEnum.class);
    enumToScreenFxml = new EnumMap<PageEnum, String>(PageEnum.class);
  }

  public static ScreenManager getInstance() {
    if (instance == null) {
      instance = new ScreenManager();
    }
    return instance;
  }

  public void appendScreenController(PageEnum enumName, ScreenController screenController) {
    this.enumToScreenController.put(enumName, screenController);
  }

  public void appendScreenFxml(PageEnum enumName, String screenFxml) {
    this.enumToScreenFxml.put(enumName, screenFxml);
  }

  public EnumMap<PageEnum, ScreenController> getEnumToScreenController() {
    return new EnumMap<PageEnum, ScreenController>(this.enumToScreenController);
  }  
  
  public EnumMap<PageEnum, String> getEnumToScreenFxml() {
    return new EnumMap<PageEnum, String>(this.enumToScreenFxml);
  }  
}
