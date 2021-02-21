package org.hbrs.se2.hausarbeit.carlookltd.process.control;

import org.hbrs.se2.hausarbeit.carlookltd.model.dao.AutoDAO;
import org.hbrs.se2.hausarbeit.carlookltd.model.objects.dto.Auto;

import java.util.ArrayList;
import java.util.List;

public class AutoSearchControl {
  /*
    Auto auto1 = new Auto(0,"BMW", 1999, "tolles Auto das schnell fährt",3);
    Auto auto2 = new Auto(1,"Ford", 2010, "ganz ok",3);
    Auto auto3 = new Auto(2,"Audi", 2018, "kaum kilometer und noch recht neu",3);
    Auto auto4 = new Auto(3,"Mercedes", 1969, "ein echter Oldie",3);
    Auto auto5 = new Auto(4,"BMW", 1986, "tolles Auto das schnell fährt",2);
    Auto auto6 = new Auto(5,"Ford", 2020, "ganz ok",2);
    Auto auto7 = new Auto(6,"Audi", 2008, "kaum kilometer und noch recht neu",2);
    Auto auto8 = new Auto(7,"Mercedes", 1969, "ein richtig echter alter Oldie",2);
*/
    private AutoSearchControl() {

    }
    public static AutoSearchControl search = null;

    public static AutoSearchControl getInstance() {
        if (search == null) {
            search = new AutoSearchControl();
        }
        return search;
    }
    public List<Auto> getAutos () {

 /*       ArrayList<Auto> liste = new ArrayList<Auto>();
        if(vertrieblerId == "Mercedes") {
            liste.add(auto1);
            liste.add(auto2);
            liste.add(auto3);
            liste.add(auto4);
        }
        if(vertrieblerId == "Mercedes") {
            liste.add(auto5);
            liste.add(auto6);
            liste.add(auto7);
            liste.add(auto8);
        }

  */
        // return liste;
        return AutoDAO.getInstance().getAutos();
    }
}
