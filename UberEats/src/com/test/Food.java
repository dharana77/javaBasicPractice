package com.test;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Food {

    private String foodID;
    private String foodName;
    private String foodDescription;
    private double foodPrice;
    private int foodCalorie;
    private String foodType;

    public Food(){
    }
    //another constructor
    public Food (String foodId, String foodName, String foodDescription, double foodPrice, int foodCalorie, String foodType){
        this.foodID = foodId;
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.foodPrice = foodPrice;
        this.foodCalorie = foodCalorie;
        this.foodType = foodType;
    }
    
    public boolean AddFood(Food food){
        // add the information of food to a TXT file
        // if the food information is added to the TXT file, it should return true;
        // if the food information cannot be added to the TXT file, it should return false;
        if(!ValidateAddFood(food)){
            return false;
        }

        try{
            FileWriter fw = new FileWriter("./food.txt", true);
            BufferedWriter writer = new BufferedWriter(fw);
            String foodInfo = getFoodInfo(food);
            System.out.println(foodInfo);
            writer.write(foodInfo);
            writer.close();
            return true;
        }catch (IOException e) {
            return false;
        }
    }

    public boolean UpdateFood(Food prevFood, Food updatedFood){
        //  Update the information of a given food in a TXT file
        // if the food information is updated in the TXT file, it should return true;
        // if the food information cannot be updated in the TXT file, it should return false;
    	if(!ValidateAddFood(updatedFood)){
            return false;
        }
    	
        Path path = Paths.get("./food.txt");

        try{
            List<String> allLines = Files.readAllLines(path);

            for(int i=0; i< allLines.size(); i++){
                String[] info = allLines.get(i).split(" ");

                double foodPrice = Double.valueOf(info[3]);
                int foodCalorie = Integer.parseInt(info[4]);
               
                if(info[0].equals(prevFood.foodID)){
                    Food targetFood = new Food(foodID=info[0], foodName=info[1], foodDescription=info[2], foodPrice, foodCalorie, foodType=info[5]+" "+info[6]);
                    if(!ValidateUpdateFood(targetFood, updatedFood)){
                        return false;
                    }
                    String updatedFoodInfo = getFoodInfo(updatedFood);
                    allLines.set(i, updatedFoodInfo);
                    break;
                }
            }
            Files.write(Path.of("./food.txt"), allLines, StandardCharsets.UTF_8);
            return true;
        }catch (IOException e) {
            return false;
        }
    }

    /*
    Food name should be between 5 and 30 characters
     */
    public  boolean ValidateFoodName(Food food){
        String foodName = food.foodName;
        int len = foodName.length();
        if(len >= 5 && len <= 30){
            return true;
        }
        return false;
    }

    /*
    Food description should be between 5 and 50 words
     */
    public boolean ValidateFoodDescription(Food food){
        String foodDescription = food.foodDescription;
        int len = foodDescription.length();
        if(len >=5 && len <=50){
            return true;
        }
        return false;
    }

    /*
      It should not be possible to add foods with more than 1500 calorie
     */
    public boolean ValidateFoodCalorie(Food food){
        int foodCalorie = food.foodCalorie;
        if(foodCalorie<=1500 && foodCalorie>=0){
            return true;
        }
        return false;
    }


    /*
    If the type of food is “Kid Food”, their calorie should be less than 800.
     */
    public boolean ValidateFoodType(Food food){
        String foodType = food.foodType;
        if(foodType == "Kid Food"){
            ValidateFoodCalorieByType(food);
        }
        if(!belongsToFoodTypeList(foodType)) {
        	return false;
        }
        return true;
    }
    
    /*
     * If foodType belongs to “Kid Food”, “Adult Food”, “Healthy Food”, or "Elderly Food", return true else false
     */
    public boolean belongsToFoodTypeList(String foodType) {
    	if(foodType == "Kid Food" || foodType == "Adult Food" || foodType == "Healthy Food" || foodType == "Elderly Food") {
    		return true;
    	}
    	return false;
    }

    public boolean ValidateFoodCalorieByType(Food food){
        int foodCalorie = food.foodCalorie;
        if(foodCalorie < 800 && foodCalorie>=0){
            return true;
        }
        return false;
    }

    /*
     The price of each food should be between $5 and $150.
     */
    public boolean ValidateFoodPrice(Food food){
        double foodPrice = food.foodPrice;
        if(foodPrice>=5 && foodPrice<=150){
            return true;
        }
        return false;
    }

    /*
    The price of foods with more than 1000 calories should be less than $100.
     */
    public boolean ValidateFoodPriceByCalorie(Food food){
        double foodPrice = food.foodPrice;
        int foodCalorie = food. foodCalorie;
        if(foodCalorie > 1000){
            if(foodPrice <100){
                return true;
            }
            return false;
        }
        return true;
    }

    //update
    /*
    It should not be possible to increase the food price by more than 10%.
     */
    public boolean ValidateUpdateFoodPrice(Food targetFood, Food updatedFood){
        double targetPrice = targetFood.foodPrice;
        double updatePrice = updatedFood.foodPrice;
        if(targetPrice*1.1 < updatePrice){
            return false;
        }
        return true;
    }
    /*
     It should not be possible to change the food calorie.
     */
    public boolean ValidateUpdateFoodCalorie(Food targetFood, Food updatedFood){
        int prevCalorie = targetFood.foodCalorie;
        int updatedCalorie = updatedFood.foodCalorie;
        if(prevCalorie == updatedCalorie){
            return true;
        }
        return false;
    }

    /*
     It should not be possible to change the type of food to “Kid Food”, but it can be possible to change “Kid Food” to any other type.
     */
    public boolean ValidateUpdateFoodType(Food prevFood, Food updatedFood){
        String prevFoodType = prevFood.foodType;
        String updatedFoodType = updatedFood.foodType;
        System.out.print("prev "+prevFoodType+"\n"+"updated "+updatedFoodType+"\n");
        
        if(!prevFoodType.equals("Kid Food")){
            if(updatedFoodType.equals("Kid Food")){
                return false;
            }
        }
        return true;
    }

    /*
    all add food validation laws
     */
    public boolean ValidateAddFood(Food food){
    	//ValidateAddFoodConsolePrint(food);
    	
    	if(!ValidateFoodName(food) || !ValidateFoodDescription(food) || !ValidateFoodCalorie(food)
                || !ValidateFoodType(food) || !ValidateFoodPrice(food) || !ValidateFoodPriceByCalorie(food)){
            return false;
        }
        return true;
    }
    
    //print test if each validation passed
    /*
    public void ValidateAddFoodConsolePrint(Food food) {
    	if(ValidateFoodName(food)) {
    		System.out.print("name passed\n");
    	}
    	if(ValidateFoodDescription(food)) {
    		System.out.print("description passed\n");
    	}
    	if(ValidateFoodCalorie(food)) {
    		System.out.print("calorie passed\n");
    	}
    	if(ValidateFoodType(food)) {
    		System.out.print("type passed\n");
    	}
    	if(ValidateFoodPrice(food)) {
    		System.out.print("price passed\n");
    	}
    	if(ValidateFoodPriceByCalorie(food)) {
    		System.out.print("calorie passed\n");
    	}
    }
    /*
    
    /*
    all update food validation laws
     */
    public boolean ValidateUpdateFood(Food targetFood, Food updatedFood){
        if(!ValidateUpdateFoodPrice(targetFood, updatedFood) || !ValidateUpdateFoodCalorie(targetFood, updatedFood) ||
            !ValidateUpdateFoodType(targetFood, updatedFood)) {
            return false;
        }
        return true;
    }

    /*
     * get food info to text line string
     */
    public String getFoodInfo(Food food){
        String foodInfo = food.foodID + " " + food.foodName + " " + food.foodDescription + " " +
                String.valueOf(food.foodPrice) + " " + String.valueOf(food.foodCalorie) + " " + food.foodType;
        return foodInfo;
    }
}


