

import config.Diconfig;
import controller.WarehouseController;

public class Main {
    public static void main(String[] args) {
        Diconfig diconfig = new Diconfig();
        WarehouseController warehouseController = diconfig.warehouseController();
        warehouseController.start();

    }

}
