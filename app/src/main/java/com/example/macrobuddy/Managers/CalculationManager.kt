package com.example.macrobuddy.Managers

import com.example.macrobuddy.Enums.Gender
import com.example.macrobuddy.Enums.Goal
import com.example.macrobuddy.Enums.RateOfActivity
import com.example.macrobuddy.Models.CalculationResult
import com.example.macrobuddy.Models.UserInformation
import java.util.*

class CalculationManager {

    companion object {
        /**
         * performs the calculation to calculate the required daily nutritional values based on the
         * users configured data.
         */
        fun performCalculation(userInformation: UserInformation): CalculationResult {
            var userAge = calculateUserAge(userInformation.dateOfBirth!!)

            //first calculate the BMR of the user
            var bmr = if (userInformation.gender == Gender.MALE.ordinal) {
                calculateMaleBMR(userInformation.currentWeight, userInformation.length, userAge)
            } else {
                calculateFemaleBMN(
                    userInformation.currentWeight,
                    userInformation.length,
                    userAge
                )
            }

            var caloricNeed =
                calculateCaloricNeed(bmr, userInformation.rateOfActivity, userInformation.goal)

            //now distribute the calories into macro nutrients and return the result
            return distributeCalories(userInformation.currentWeight, caloricNeed)
        }


        /**
         * Distributes the calories over the macro nutrients (carbohydrates, vats, protein) and returns the result
         * as a calculation result.
         */
        private fun distributeCalories(
            usersWeight: Double,
            caloricNeed: Double
        ): CalculationResult {
            //calculate the vats and protein
            var requiredProtein = usersWeight * 2.2
            var requiredVats = requiredProtein * 0.66

            //now see how many calories are left and convert them to carbohydrates
            var caloriesLeft = caloricNeed - (requiredProtein * 4) - (requiredVats * 9)
            var requiredCarbs = caloriesLeft / 4

            return CalculationResult(roundTwoDecimals(caloricNeed), roundTwoDecimals(requiredProtein),
                roundTwoDecimals(requiredVats), roundTwoDecimals(requiredCarbs))
        }

        /**
         * Calculates the daily caloric need of a user based on his/her bmr, daily activity rate and goal
         */
        private fun calculateCaloricNeed(bmr: Double, rateOfActivity: Int, goal: Int): Double {
            //calculate the raw caloric need without taking the goal of the user into consideration
            var rawCaloricNeed = when (rateOfActivity) {
                RateOfActivity.NOT_ACTIVE.ordinal -> {
                    bmr * 1.2
                }
                RateOfActivity.LIGHTLY_ACTIVE.ordinal -> {
                    bmr * 1.375
                }
                RateOfActivity.MODERATELEY_ACTIVE.ordinal -> {
                    bmr * 1.55
                }
                RateOfActivity.VERY_ACTIVE.ordinal -> {
                    bmr * 1.725
                }
                RateOfActivity.EXTREMELY_ACTIVE.ordinal -> {
                    bmr * 1.9
                }
                else -> {
                    bmr
                }
            }

            //finally modify the raw caloric need based on the users goal and return
            return when (goal) {
                Goal.MAINTAINANCE.ordinal -> {
                    rawCaloricNeed
                }
                Goal.WEIGHT_LOSS.ordinal -> {
                    rawCaloricNeed - 250
                }
                Goal.WEIGHT_GAIN.ordinal -> {
                    rawCaloricNeed + 250
                }
                else -> {
                    rawCaloricNeed
                }

            }
        }

        /**
         * Calculate the Basal metabolic rate (daily calories required when doing nothing at all) for males
         */
        private fun calculateMaleBMR(weight: Double, length: Int, age: Int): Double {
            return 66 + (13.7 * weight) + (5 * length) - (6.8 * age)
        }

        /**
         * Calculate the Basal metabolic rate (daily calories required when doing nothing at all) for females
         */
        private fun calculateFemaleBMN(weight: Double, length: Int, age: Int): Double {
            return 655 + (9.6 * weight) + (1.8 + length) - (4.7 * age)
        }

        /**
         * Get the age of the user based on his birth date
         */
        private fun calculateUserAge(birthDate: Date): Int {
            return Calendar.getInstance().get(Calendar.YEAR) - (birthDate!!.year + 1900)
        }

        /**
         * Rounds a Double to two decimals
         */
        private fun roundTwoDecimals(number :Double) : Double {
          return Math.round(number * 100.0) / 100.0
        }

    }
}