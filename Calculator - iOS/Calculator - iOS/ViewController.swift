//
//  ViewController.swift
//  Calculator - iOS
//
//  Created by Tom Kocik on 1/6/16.
//  Copyright © 2016 Tom Kocik. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet var firstOperandInput: UITextField!
    @IBOutlet var secondOperandInput: UITextField!
    @IBOutlet var addButton: UIButton!
    @IBOutlet var subtractButton: UIButton!
    @IBOutlet var multiplyButton: UIButton!
    @IBOutlet var divideButton: UIButton!
    @IBOutlet var answerLabel: UILabel!

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func calculate(sender: AnyObject)
    {
        let calculator = Calculator.init();
        let firstOperand = Double.init(firstOperandInput.text!);
        let secondOperand = Double.init(secondOperandInput.text!);
        
        var answer:Double = 0;
        
        if(sender as! UIButton == addButton)
        {
            answer = calculator.add(firstOperand!, secondOperand: secondOperand!);
        }
        
        if(sender as! UIButton == subtractButton)
        {
            answer = calculator.subtract(firstOperand!, secondOperand: secondOperand!);
        }
        
        if(sender as! UIButton == multiplyButton)
        {
            answer = calculator.multiply(firstOperand!, secondOperand: secondOperand!);
        }
        
        if(sender as! UIButton == divideButton)
        {
            answer = calculator.divide(firstOperand!, secondOperand: secondOperand!);
        }
        
        answerLabel.text = String.init(answer);
    }
}

