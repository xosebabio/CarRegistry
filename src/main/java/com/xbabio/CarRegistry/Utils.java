package com.xbabio.CarRegistry;

import com.xbabio.CarRegistry.domain.Car;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

public class Utils {
    public static Optional<Car> carById(ArrayList<Car> cars, int id) {
        return cars.stream()
                .filter(car -> car.getId() == id)
                .findFirst();
    }

    public static void updateCarById(ArrayList<Car> carList, Car newCar) {
        for (Car oldCar : carList) {
            if (oldCar.getId().equals(newCar.getId())) {
                oldCar.setBrand(newCar.getBrand());
                oldCar.setModel(newCar.getModel());
                oldCar.setMilleage(newCar.getMilleage());
                oldCar.setPrice(newCar.getPrice());
                oldCar.setYear(newCar.getYear());
                oldCar.setDescription(newCar.getDescription());
                oldCar.setColour(newCar.getColour());
                oldCar.setFuelType(newCar.getFuelType());
                oldCar.setNumDoors(newCar.getNumDoors());
                break;
            }
        }
    }

    public static void deleteCar(int id, ArrayList<Car> carList) {
        Iterator<Car> iterator = carList.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            if (car.getId().equals(id)) {
                iterator.remove();
            }
        }
    }
}
