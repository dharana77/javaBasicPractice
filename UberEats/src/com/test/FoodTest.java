package com.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FoodTest {
	
	//foodAdd success
	@Test
	void testAddFood() {
		//success when rightly added
		Food food = new Food("1", "sandwich", "famous", 5, 1000, "Kid Food");
		assertEquals(true, food.AddFood(food));
		
		//fail when AddValidation failed : in case calorie is negative or else validation fails: details below
		food = new Food("1", "sandwich", "famous", -1, 1000, "Kid Food");
		assertEquals(false, food.AddFood(food));
	}
	
	/*
	 * food add validations
	 *
	 */
	
	//Food name should be between 5 and 30 characters
	@Test
	void testValidateFoodName() {
		Food food = new Food();
		//return true when food name length is 5
		Food foodNameLengthIs5 = new Food("1", "12345", "famous", 5, 1000, "Kid Food");
		assertEquals(true, food.ValidateFoodName(foodNameLengthIs5));
		
		//return true when food name length is 10
		Food foodNameLenIs10 = new Food("1", "0123456789", "famous", 5, 1000, "Kid Food");
		assertEquals(true, food.ValidateFoodName(foodNameLenIs10));
		
		//return false when food name is not between 5~10
		Food foodNameLenIs3 = new Food("1", "012", "famous", 5, 1000, "Kid Food");
		assertEquals(false, food.ValidateFoodName(foodNameLenIs3));
	}
	
	
	//Food description should be between 5 and 50 words
	@Test 
	void testValidateFoodDescription(){
		//success test when description length is 5
		Food food = new Food("1", "12345", "famous", 5, 1000, "Kid Food");
		assertEquals(true, food.ValidateFoodDescription(food));
		
		//success test when description length is 50
		String stringLengthIs50 = "0123456789/123456789/123456789/123456789/123456789";
		food = new Food("1", "12345", stringLengthIs50, 5, 1000, "Kid Food");
		assertEquals(true, food.ValidateFoodDescription(food));
		
		//fail test when description length is not between 5~50
		food = new Food("1", "12345", "333", 5, 1000, "Kid Food");
		assertEquals(false, food.ValidateFoodDescription(food));
	}
	
	//It should not be possible to add foods with more than 1500 calorie
	@Test
	void testValidateFoodCalorie() {
		//success test when calorie is 1500
		Food food = new Food("1", "12345", "famous", 5, 1500, "Kid Food");
		assertEquals(true, food.ValidateFoodCalorie(food));
		
		//fail test when calorie is 1500
		food = new Food("1", "12345", "famous", 5, 1501, "Kid Food");
		assertEquals(false, food.ValidateFoodCalorie(food));
		
		//fail test when calorie is lower than 0
		food = new Food("1", "12345", "famous", 5, -1, "Kid Food");
		assertEquals(false, food.ValidateFoodCalorie(food));
	}
	
	//If the type of food is “Kid Food”, their calorie should be less than 800.
	@Test
	void testValidateFoodType() {
		//success when food type is Kid Food calorie is 799 
		Food food = new Food("1", "12345", "famous", 5, 799, "Kid Food");
		assertEquals(true, food.ValidateFoodType(food));
		
		//fail when food type is Kid Food calorie is 800
		food = new Food("1", "12345", "famous", 5, 800, "Kid Food");
		assertEquals(true, food.ValidateFoodType(food));
		
		//success when food type is not Kid Food and calories is between 0~1500
		food = new Food("1", "12345", "famous", 5, 600, "Adult Food");
		assertEquals(true, food.ValidateFoodType(food));
		
		//fail when food type is not “Kid Food”, “Adult Food”, “Healthy Food”, or "Elderly Food"
		food = new Food("1", "12345", "famous", 5, 600, "Adult Foo");
		assertEquals(false, food.ValidateFoodType(food));
	}
	
	//The price of each food should be between $5 and $150.
	@Test
	void tesetValidateFoodPrice() {
		//success if food price is 5
		Food food = new Food("1", "12345", "famous", 5, 600, "Adult Food");
		assertEquals(true, food.ValidateFoodPrice(food));
		
		//success if food price is 150
		food = new Food("1", "12345", "famous", 150, 600, "Adult Food");
		assertEquals(true, food.ValidateFoodPrice(food));
		
		//fail if food price is not between 5~150
		food = new Food("1", "12345", "famous", 3, 600, "Adult Food");
		assertEquals(false, food.ValidateFoodPrice(food));
		
	}
	
	//The price of foods with more than 1000 calories should be less than $100.
	@Test
	void ValidateFoodCalorieWithPrice() {
		//test success when calorie is 1001 and price is 99
		Food food = new Food("1", "12345", "famous", 99, 1001, "Adult Food");
		assertEquals(true, food.ValidateFoodPriceByCalorie(food));
		
		//test fail when calorie is 1001 and price is 100
		food = new Food("1", "12345", "famous", 100, 1001, "Adult Food");
		assertEquals(false, food.ValidateFoodPriceByCalorie(food));
	}
	
	
	//foodUpdate success
	@Test
	void testUpdateFood() {
		Food prevFood = new Food("1", "sandwich", "famous", 5, 1000, "Kid Food");
		Food updatedFood =  new Food("1", "sandwich", "famous", 5, 1000, "Kid Food");
		assertEquals(true, prevFood.UpdateFood(prevFood, updatedFood));
	}
	
	
	/*
    It should not be possible to increase the food price by more than 10%.
     */
	@Test
	void testValidateUpdateFoodPrice() {
		Food food = new Food();
		
		//success when updated food price is not more than 10%
		Food prevFood = new Food("1", "sandwich", "famous", 5, 1000, "Kid Food");
		Food updatedFood = new Food("1", "sandwich", "famous", 5.5, 1000, "Kid Food");
		assertEquals(true, food.ValidateUpdateFoodPrice(prevFood, updatedFood));
		
		//fails when updated food price is more than 10%
		updatedFood = new Food("1", "sandwich", "famous", 5.6, 1000, "Kid Food");
		assertEquals(false, food.ValidateUpdateFoodPrice(prevFood, updatedFood));
	}
	
	/*
    It should not be possible to change the food calorie.
    */
	@Test
	void testValidateUpdateFoodCalorie() {
		Food food = new Food();
		
		//success when prevFood's calorie is same with updateFood's 
		Food prevFood = new Food("1", "sandwich", "famous", 5, 1000, "Kid Food");
		Food updatedFood = new Food("1", "sandwich", "famous", 5, 1000, "Kid Food");
		assertEquals(true, food.ValidateUpdateFoodCalorie(prevFood, updatedFood));
		
		//fails when updated is bigger
		updatedFood = new Food("1", "sandwich", "famous", 5, 1001, "Kid Food");
		assertEquals(false, food.ValidateUpdateFoodCalorie(prevFood, updatedFood));
		
		//fails when updated is smaller
		updatedFood = new Food("1", "sandwich", "famous", 5, 999, "Kid Food");
		assertEquals(false, food.ValidateUpdateFoodCalorie(prevFood, updatedFood));
				
	}
	
	/*
    It should not be possible to change the type of food to “Kid Food”, but it can be possible to change “Kid Food” to any other type.
    */
	@Test
	void testValidateUpdateFoodType() {
		Food food = new Food();
		
		//fail when Adult Food to Kid Food update
		Food prevFood = new Food("1", "sandwich", "famous", 5, 1000, "Adult Food");
		Food updatedFood = new Food("1", "sandwich", "famous", 5, 1000, "Kid Food");
		assertEquals(false, food.ValidateUpdateFoodType(prevFood, updatedFood));
		
		//fail when Healthy Food to Kid Food update
		prevFood = new Food("1", "sandwich", "famous", 5, 1000, "Healthy Food");
		assertEquals(false, food.ValidateUpdateFoodType(prevFood, updatedFood));
		
		//fail when Elderly Food to Kid Food update
		prevFood = new Food("1", "sandwich", "famous", 5, 1000, "Elderly Food");
		assertEquals(false, food.ValidateUpdateFoodType(prevFood, updatedFood));
		
		//success when Kid Food to Kid Food update
		prevFood = new Food("1", "sandwich", "famous", 5, 1000, "Kid Food");
		updatedFood = new Food("1", "sandwich", "famous", 5, 1000, "Kid Food");
		assertEquals(true, food.ValidateUpdateFoodType(prevFood, updatedFood));
		
		
		//success when Kid Food to Adult Food update
		updatedFood = new Food("1", "sandwich", "famous", 5, 1000, "Adult Food");
		assertEquals(true, food.ValidateUpdateFoodType(prevFood, updatedFood));
		
		//success when Kid Food to Elderly Food update
		updatedFood = new Food("1", "sandwich", "famous", 5, 1000, "Elderly Food");
		assertEquals(true, food.ValidateUpdateFoodType(prevFood, updatedFood));
		
		//success when Kid Food to Healthy Food update
		updatedFood = new Food("1", "sandwich", "famous", 5, 1000, "Healthy Food");
		assertEquals(true, food.ValidateUpdateFoodType(prevFood, updatedFood));
		
	}
	

}
