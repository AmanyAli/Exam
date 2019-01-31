package com.am.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author Amany Ali
 * 
 *         Condition checker class to evaluate condition
 *
 */
public class ConditionCheckerUtil {
	public static final String END_CONDITION = ";";
	public static final String COMA = ",";
	public static final String HASH = "#";

	public static final String Equal = "==";
	public static final String Greater = ">";
	public static final String Less = "<";
	public static final String GreaterorEqual = ">=";
	public static final String LessorEqual = "<=";
	public static final String AND = "&&";
	public static final String OR = "||";
	public static final String OPEN_PARENTHESIS = "(";
	public static final String CLOSE_PARENTHESIS = ")";

	
	public static class ConditionDto {

		String condition;

		Double value;

		public Double getValue() {
			return value;
		}

		public void setValue(Double value) {
			this.value = value;
		}

		public String getCondition() {
			return condition;
		}

		public void setCondition(String condition) {
			this.condition = condition;
		}

	}

	public static boolean evalCond(ConditionDto condition) {

		boolean valid = false;
		Double value = condition.getValue();
		if (value != null) {
			condition.setCondition(com.am.util.StringUtil.append(false, value, condition.getCondition()));
			condition.setCondition(condition.getCondition().replaceAll(END_CONDITION,
					com.am.util.StringUtil.append(false, END_CONDITION, value)));
		}

		String updatedCond = condition.getCondition().replaceAll(HASH, "").replaceAll(END_CONDITION, "").replaceAll(COMA,
				"");

		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");

		try {
			Object obj = engine.eval(updatedCond);

			return Boolean.parseBoolean(obj.toString());
		} catch (ScriptException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return valid;

	}

	public static int getCloseIndex(String exprssion) {
		int index = -1;
		Stack<Character> stk = new Stack<Character>();
		if (exprssion == null || exprssion.length() == 0) {
			return index;
		} else {
			for (int i = 0; i < exprssion.length(); i++) {

				if (exprssion.charAt(i) == '(') {
					stk.push(exprssion.charAt(i));
				} else if (exprssion.charAt(i) == ')') {
					if (!stk.isEmpty() && stk.peek() == '(') {
						stk.pop();
						if (stk.isEmpty()) {
							return i;
						}

					} else {
						return index;
					}
				}
			}

			// if stack is empty finally , it means symbol is balance in
			// expression

		}
		return index;
	}

	public static boolean checkBalancedParentesis(String expr) {
		if (expr.isEmpty())
			return true;

		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < expr.length(); i++) {
			char current = expr.charAt(i);
			if (current == '{' || current == '(' || current == '[') {
				stack.push(current);
			}
			if (current == '}' || current == ')' || current == ']') {
				if (stack.isEmpty())
					return false;
				char last = stack.peek();
				if (current == '}' && last == '{' || current == ')' && last == '(' || current == ']' && last == '[')
					stack.pop();
				else
					return false;
			}
		}
		return stack.isEmpty() ? true : false;
	}

	public static List<String> conditionList = new ArrayList<String>() {

		private static final long serialVersionUID = 1L;

		{
			add(Equal);
			add(Greater);
			add(Less);
			add(GreaterorEqual);
			add(LessorEqual);
		}
	};

	public static List<String> andOrConditionList = new ArrayList<String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add(AND);
			add(OR);

		}
	};
}
