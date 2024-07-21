package com.sergTito;


import com.sergTito.crud.CreateAndGetNewEmployee;
import com.sergTito.crud.ReadEmployee;
import com.sergTito.crud.UpdateEmployee;

public class Main {
    public static void main(String[] args) {

//        CreateAndGetNewEmployee createNewEmployee = new CreateAndGetNewEmployee();
//        createNewEmployee.createEmployee("Margaritaaa","Andropovna","SMM",850);
//        createNewEmployee.getId();


//        ReadEmployee r = new ReadEmployee();
//        r.read();

        UpdateEmployee updateEmployee = new UpdateEmployee();
        updateEmployee.update(0,15233);
//        updateEmployee.updateAll();

    }
}