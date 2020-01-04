package com.example.macrobuddy.Managers

import com.example.macrobuddy.Enums.Gender
import com.example.macrobuddy.Models.CalculationResult
import com.example.macrobuddy.Models.UserInformation

class CalculationManager {

    companion object{
        /**
         * performs the calculation to calculate the required daily nutritional values based on the
         * users configured data.
         */
        fun performCalculation(userInformation : UserInformation) : CalculationResult{
            if(userInformation.gender == Gender.MALE.ordinal){

            }else{

            }
        }
        
    }


}