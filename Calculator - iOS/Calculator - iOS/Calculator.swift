//
//  Calculator.swift
//  Calculator - iOS
//
//  Created by Tom Kocik on 1/6/16.
//  Copyright © 2016 Tom Kocik. All rights reserved.
//

import Foundation

class Calculator {
    enum Operator
    {
        case ADD
        case SUBTRACT
        case MULTIPLY
        case DIVIDE
    }
    
    func add(firstOperand: Double, secondOperand: Double) -> Double
    {
        return firstOperand + secondOperand;
    }
    
    func subtract(firstOperand: Double, secondOperand: Double) -> Double
    {
        return firstOperand - secondOperand;
    }
    
    func divide(firstOperand: Double, secondOperand:Double) -> Double
    {
        return firstOperand / secondOperand;
    }
    
    func multiply(firstOperand: Double, secondOperand:Double) -> Double
    {
        return firstOperand * secondOperand;
    }
}