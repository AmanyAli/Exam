package com.am.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.am.configuration.DatabaseConfiguration;
import com.am.util.ConditionCheckerUtil;
import com.am.util.ConditionCheckerUtil.ConditionDto;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { DatabaseConfiguration.class })

public class ConditionCheckerUtilTest extends TestCase {

	@Before
	public void setup() {

	}

	@Test
	public void evalCondTest() {
		ConditionDto conditionDto = new ConditionDto();
		// 0.0#=,0.0,&&;2#=,0.0,
		conditionDto.setCondition(0 + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA
				+ 0 + ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND + ConditionCheckerUtil.END_CONDITION + 2
				+ ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + 0
				+ ConditionCheckerUtil.COMA);

		assertFalse(ConditionCheckerUtil.evalCond(conditionDto ));

		conditionDto = new ConditionDto();
		conditionDto.setCondition(0 + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA
				+ 0 + ConditionCheckerUtil.COMA + ConditionCheckerUtil.OR + ConditionCheckerUtil.END_CONDITION + 2
				+ ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + 0
				+ ConditionCheckerUtil.COMA);

		assertTrue(ConditionCheckerUtil.evalCond(conditionDto));

		conditionDto = new ConditionDto();
		conditionDto.setCondition(2 + ConditionCheckerUtil.HASH + ConditionCheckerUtil.GreaterorEqual
				+ ConditionCheckerUtil.COMA + 0 + ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND
				+ ConditionCheckerUtil.END_CONDITION + 1 + ConditionCheckerUtil.HASH + ConditionCheckerUtil.LessorEqual
				+ ConditionCheckerUtil.COMA + 4 + ConditionCheckerUtil.COMA);

		assertTrue(ConditionCheckerUtil.evalCond(conditionDto));

		conditionDto = new ConditionDto();
		conditionDto.setCondition(2 + ConditionCheckerUtil.HASH + ConditionCheckerUtil.GreaterorEqual
				+ ConditionCheckerUtil.COMA + 0 + ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND
				+ ConditionCheckerUtil.END_CONDITION + 5 + ConditionCheckerUtil.HASH + ConditionCheckerUtil.LessorEqual
				+ ConditionCheckerUtil.COMA + 4 + ConditionCheckerUtil.COMA);

		assertFalse(ConditionCheckerUtil.evalCond(conditionDto ));

		// test when send value as param

		conditionDto = new ConditionDto();

		String condition = ConditionCheckerUtil.LessorEqual + ConditionCheckerUtil.COMA + 3 + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.AND + ConditionCheckerUtil.END_CONDITION + ConditionCheckerUtil.GreaterorEqual
				+ ConditionCheckerUtil.COMA + 2 + ConditionCheckerUtil.COMA;
		conditionDto.setCondition(condition);
		conditionDto.setValue(1.0);

		assertFalse(ConditionCheckerUtil.evalCond(conditionDto ));
		conditionDto = new ConditionDto();
		conditionDto.setCondition(condition);
		conditionDto.setValue(2.0);
		assertTrue(ConditionCheckerUtil.evalCond(conditionDto ));

		condition = ConditionCheckerUtil.LessorEqual + ConditionCheckerUtil.COMA + 3 + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.AND + ConditionCheckerUtil.END_CONDITION + ConditionCheckerUtil.GreaterorEqual
				+ ConditionCheckerUtil.COMA + 0 + ConditionCheckerUtil.COMA;
		conditionDto = new ConditionDto();
		conditionDto.setCondition(condition);
		conditionDto.setValue(2.0);
		assertTrue(ConditionCheckerUtil.evalCond(conditionDto));
		conditionDto = new ConditionDto();
		conditionDto.setCondition(condition);
		conditionDto.setValue(1.0);
		assertTrue(ConditionCheckerUtil.evalCond(conditionDto));
		conditionDto = new ConditionDto();
		conditionDto.setCondition(condition);
		conditionDto.setValue(0.0);
		assertTrue(ConditionCheckerUtil.evalCond(conditionDto ));
		conditionDto = new ConditionDto();
		conditionDto.setCondition(condition);
		conditionDto.setValue(3.0);
		assertTrue(ConditionCheckerUtil.evalCond(conditionDto));
		conditionDto = new ConditionDto();
		conditionDto.setCondition(condition);
		conditionDto.setValue(4.0);
		assertFalse(ConditionCheckerUtil.evalCond(conditionDto));

	}
	
	@Test
	public void evalConditionWithOr(){
		 ConditionDto conditionDto = new ConditionDto();
		 //
		 conditionDto.setCondition("5.0" + ConditionCheckerUtil.HASH +
		 ConditionCheckerUtil.Equal
		 + ConditionCheckerUtil.COMA + "1" + ConditionCheckerUtil.COMA +
		 ConditionCheckerUtil.AND
		 + ConditionCheckerUtil.END_CONDITION 
		 + "0.0"
		 + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal +
		 ConditionCheckerUtil.COMA + "1"
		 + ConditionCheckerUtil.COMA + ConditionCheckerUtil.OR +
		 ConditionCheckerUtil.END_CONDITION + "1.0"
		 + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal +
		 ConditionCheckerUtil.COMA + "1.0"+ ConditionCheckerUtil.COMA);
		 assertTrue(ConditionCheckerUtil.evalCond(conditionDto));
		 
		 
		 conditionDto = new ConditionDto();
		 //
		 conditionDto.setCondition("5.0" + ConditionCheckerUtil.HASH +
		 ConditionCheckerUtil.Equal
		 + ConditionCheckerUtil.COMA + "1" + ConditionCheckerUtil.COMA +
		 ConditionCheckerUtil.AND
		 + ConditionCheckerUtil.END_CONDITION 
		 + "0.0"
		 + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal +
		 ConditionCheckerUtil.COMA + "1"
		 + ConditionCheckerUtil.COMA + ConditionCheckerUtil.OR +
		 ConditionCheckerUtil.END_CONDITION + "1.0"
		 + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal +
		 ConditionCheckerUtil.COMA + "2.0"+ ConditionCheckerUtil.COMA);
		 assertFalse(ConditionCheckerUtil.evalCond(conditionDto));
		 
		 conditionDto = new ConditionDto();
			//
			 conditionDto.setCondition("0.0" + ConditionCheckerUtil.HASH +
					 ConditionCheckerUtil.Equal
					 + ConditionCheckerUtil.COMA + "1" + ConditionCheckerUtil.COMA +
					 ConditionCheckerUtil.OR
					 + ConditionCheckerUtil.END_CONDITION
					 + "1.0"
					 + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal +
					 ConditionCheckerUtil.COMA + "1"
					 + ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND +
					 ConditionCheckerUtil.END_CONDITION + "0.0"
					 + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal +
					 ConditionCheckerUtil.COMA + "1.0"+ ConditionCheckerUtil.COMA);
			 assertFalse(ConditionCheckerUtil.evalCond(conditionDto));
			 
			 
			 conditionDto = new ConditionDto();
				//
				 conditionDto.setCondition("0.0" + ConditionCheckerUtil.HASH +
						 ConditionCheckerUtil.Equal
						 + ConditionCheckerUtil.COMA + "0.0" + ConditionCheckerUtil.COMA +
						 ConditionCheckerUtil.OR
						 + ConditionCheckerUtil.END_CONDITION
						 + "1.0"
						 + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal +
						 ConditionCheckerUtil.COMA + "1"
						 + ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND +
						 ConditionCheckerUtil.END_CONDITION + "0.0"
						 + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal +
						 ConditionCheckerUtil.COMA + "1.0"+ ConditionCheckerUtil.COMA);
				 assertTrue(ConditionCheckerUtil.evalCond(conditionDto));
				 
				 
		 conditionDto = new ConditionDto();
					//
					 conditionDto.setCondition("0.0" + ConditionCheckerUtil.HASH +
							 ConditionCheckerUtil.Equal
							 + ConditionCheckerUtil.COMA + "1" + ConditionCheckerUtil.COMA +
							 ConditionCheckerUtil.AND
							 + ConditionCheckerUtil.END_CONDITION
							 + "1.0"
							 + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal +
							 ConditionCheckerUtil.COMA + "1"
							 + ConditionCheckerUtil.COMA + ConditionCheckerUtil.OR +
							 ConditionCheckerUtil.END_CONDITION + "0.0"
							 + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal +
							 ConditionCheckerUtil.COMA + "1.0"+ ConditionCheckerUtil.COMA);
			 
	
					 assertFalse(ConditionCheckerUtil.evalCond(conditionDto));
					 
					 
					 conditionDto = new ConditionDto();
						//
						conditionDto.setCondition("0.0" + ConditionCheckerUtil.HASH +
								 ConditionCheckerUtil.Equal
								 + ConditionCheckerUtil.COMA + "1" + ConditionCheckerUtil.COMA +
								 ConditionCheckerUtil.AND
								 + ConditionCheckerUtil.END_CONDITION 
								 + "1.0"
								 + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal +
								 ConditionCheckerUtil.COMA + "1"
								 + ConditionCheckerUtil.COMA + ConditionCheckerUtil.OR +
								 ConditionCheckerUtil.END_CONDITION + "1.0"
								 + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal +
								 ConditionCheckerUtil.COMA + "1.0"+ ConditionCheckerUtil.COMA);
					 
	
						 assertTrue(ConditionCheckerUtil.evalCond(conditionDto));
						 
						 
						 
						 conditionDto = new ConditionDto();
							//
							conditionDto.setCondition("0.0" + ConditionCheckerUtil.HASH +
									 ConditionCheckerUtil.Equal
									 + ConditionCheckerUtil.COMA + "1" + ConditionCheckerUtil.COMA +
									 ConditionCheckerUtil.AND
									 + ConditionCheckerUtil.END_CONDITION
									 + "1.0"
									 + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal +
									 ConditionCheckerUtil.COMA + "1"
									 + ConditionCheckerUtil.COMA + ConditionCheckerUtil.OR +
									 ConditionCheckerUtil.END_CONDITION + "0.0"
									 + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal +
									 ConditionCheckerUtil.COMA + "0.0"+ ConditionCheckerUtil.COMA+ ConditionCheckerUtil.OR +
									 ConditionCheckerUtil.END_CONDITION + "0.0"
									 + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal +
									 ConditionCheckerUtil.COMA + "1.0"+ ConditionCheckerUtil.COMA);
						 
						 

							 assertTrue(ConditionCheckerUtil.evalCond(conditionDto));
							 
	}

	@Test
	public void evalCondWith3Test() {
		ConditionDto conditionDto = new ConditionDto();

		// 0.0#=,0.0,&&;EL5a3a59cbe79f516669f292df#=,0.0,
		conditionDto.setCondition("2.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal
				+ ConditionCheckerUtil.COMA + "2" + ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND
				+ ConditionCheckerUtil.END_CONDITION + "3.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal
				+ ConditionCheckerUtil.COMA + "3" + ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND
				+ ConditionCheckerUtil.END_CONDITION + "1.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal
				+ ConditionCheckerUtil.COMA + "1" + ConditionCheckerUtil.COMA);

		assertTrue(ConditionCheckerUtil.evalCond(conditionDto));

		conditionDto = new ConditionDto();
		conditionDto.setCondition("2.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal
				+ ConditionCheckerUtil.COMA + "2" + ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND
				+ ConditionCheckerUtil.END_CONDITION + "3.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal
				+ ConditionCheckerUtil.COMA + "3" + ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND
				+ ConditionCheckerUtil.END_CONDITION + "1.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal
				+ ConditionCheckerUtil.COMA + "2" + ConditionCheckerUtil.COMA);

		assertFalse(ConditionCheckerUtil.evalCond(conditionDto));

		conditionDto = new ConditionDto();
		conditionDto.setCondition("2.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal
				+ ConditionCheckerUtil.COMA + "2" + ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND
				+ ConditionCheckerUtil.END_CONDITION + "2.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal
				+ ConditionCheckerUtil.COMA + "2" + ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND
				+ ConditionCheckerUtil.END_CONDITION + "2.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal
				+ ConditionCheckerUtil.COMA + "3" + ConditionCheckerUtil.COMA);

		assertFalse(ConditionCheckerUtil.evalCond(conditionDto));

		conditionDto = new ConditionDto();
		conditionDto.setCondition("2.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal
				+ ConditionCheckerUtil.COMA + "2" + ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND
				+ ConditionCheckerUtil.END_CONDITION + "2.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal
				+ ConditionCheckerUtil.COMA + "2" + ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND
				+ ConditionCheckerUtil.END_CONDITION + "2.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal
				+ ConditionCheckerUtil.COMA + "2" + ConditionCheckerUtil.COMA);

		assertTrue(ConditionCheckerUtil.evalCond(conditionDto));

	}

	@Test
	public void evalCondWithManyTest() {
		ConditionDto conditionDto = new ConditionDto();
		conditionDto.setCondition(ConditionCheckerUtil.OPEN_PARENTHESIS + "0.0" + ConditionCheckerUtil.HASH
				+ ConditionCheckerUtil.Less + ConditionCheckerUtil.COMA + "1.0" + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.OR + ConditionCheckerUtil.END_CONDITION + "1.0" + ConditionCheckerUtil.HASH
				+ ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0" + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.AND + ConditionCheckerUtil.END_CONDITION + "0.0" + ConditionCheckerUtil.HASH
				+ ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0" + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.AND + ConditionCheckerUtil.END_CONDITION + "1.0" + ConditionCheckerUtil.HASH
				+ ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "1.0" + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.AND + ConditionCheckerUtil.END_CONDITION + "1.0" + ConditionCheckerUtil.HASH
				+ ConditionCheckerUtil.GreaterorEqual + ConditionCheckerUtil.COMA + "1.0" + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.CLOSE_PARENTHESIS);
		// cond===(0.0#<,1.0,||;1.0#=,0.0,&&;0.0#=,0.0,&&;1.0#=,1.0,&&;1.0#>=,1.0,)
		// valid============true
		assertTrue(ConditionCheckerUtil.evalCond(conditionDto));

		conditionDto = new ConditionDto();
		conditionDto.setCondition(ConditionCheckerUtil.OPEN_PARENTHESIS + "1.0" + ConditionCheckerUtil.HASH
				+ ConditionCheckerUtil.Less + ConditionCheckerUtil.COMA + "1.0" + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.OR + ConditionCheckerUtil.END_CONDITION + "1.0" + ConditionCheckerUtil.HASH
				+ ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0" + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.AND + ConditionCheckerUtil.END_CONDITION + "0.0" + ConditionCheckerUtil.HASH
				+ ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0" + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.AND + ConditionCheckerUtil.END_CONDITION + "1.0" + ConditionCheckerUtil.HASH
				+ ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "1.0" + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.AND + ConditionCheckerUtil.END_CONDITION + "1.0" + ConditionCheckerUtil.HASH
				+ ConditionCheckerUtil.GreaterorEqual + ConditionCheckerUtil.COMA + "1.0" + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.CLOSE_PARENTHESIS);
		// cond===(1.0#<,1.0,||;1.0#=,0.0,&&;0.0#=,0.0,&&;1.0#=,1.0,&&;1.0#>=,1.0,)
		// valid============false
		assertFalse(ConditionCheckerUtil.evalCond(conditionDto));
	}

	@Test
	public void evalCondWithParentseisTest() {
		ConditionDto conditionDto = new ConditionDto();

		// 0.0#=,0.0,&&;EL5a3a59cbe79f516669f292df#=,0.0,
		conditionDto.setCondition(ConditionCheckerUtil.OPEN_PARENTHESIS + "3.0" + ConditionCheckerUtil.HASH
				+ ConditionCheckerUtil.Less + ConditionCheckerUtil.COMA + "1.0" + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.AND + ConditionCheckerUtil.END_CONDITION + "0.0" + ConditionCheckerUtil.HASH
				+ ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0" + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.CLOSE_PARENTHESIS + ConditionCheckerUtil.OR + ConditionCheckerUtil.END_CONDITION
				+ "0.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0"
				+ ConditionCheckerUtil.COMA);

		assertTrue(ConditionCheckerUtil.evalCond(conditionDto));

		conditionDto = new ConditionDto();
		conditionDto.setCondition(ConditionCheckerUtil.OPEN_PARENTHESIS + "3.0" + ConditionCheckerUtil.HASH
				+ ConditionCheckerUtil.Less + ConditionCheckerUtil.COMA + "1.0" + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.AND + ConditionCheckerUtil.END_CONDITION + "0.0" + ConditionCheckerUtil.HASH
				+ ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0" + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.CLOSE_PARENTHESIS + ConditionCheckerUtil.OR + ConditionCheckerUtil.END_CONDITION
				+ "1.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0"
				+ ConditionCheckerUtil.COMA);

		assertFalse(ConditionCheckerUtil.evalCond(conditionDto));

		conditionDto = new ConditionDto();
		conditionDto.setCondition(ConditionCheckerUtil.OPEN_PARENTHESIS + "0.0" + ConditionCheckerUtil.HASH
				+ ConditionCheckerUtil.Less + ConditionCheckerUtil.COMA + "1.0" + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.OR + ConditionCheckerUtil.END_CONDITION + "0.0" + ConditionCheckerUtil.HASH
				+ ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0" + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.CLOSE_PARENTHESIS + ConditionCheckerUtil.OR + ConditionCheckerUtil.END_CONDITION
				+ "1.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0"
				+ ConditionCheckerUtil.COMA);

		assertTrue(ConditionCheckerUtil.evalCond(conditionDto));

		conditionDto = new ConditionDto();
		conditionDto.setCondition(ConditionCheckerUtil.OPEN_PARENTHESIS + "8.0" + ConditionCheckerUtil.HASH
				+ ConditionCheckerUtil.Less + ConditionCheckerUtil.COMA + "1.0" + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.OR + ConditionCheckerUtil.END_CONDITION + "7.0" + ConditionCheckerUtil.HASH
				+ ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0" + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.CLOSE_PARENTHESIS + ConditionCheckerUtil.OR + ConditionCheckerUtil.END_CONDITION
				+ "1.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.LessorEqual + ConditionCheckerUtil.COMA
				+ "0.0" + ConditionCheckerUtil.COMA);

		assertFalse(ConditionCheckerUtil.evalCond(conditionDto));

		conditionDto = new ConditionDto();
		conditionDto.setCondition(ConditionCheckerUtil.OPEN_PARENTHESIS

				+ ConditionCheckerUtil.OPEN_PARENTHESIS + "3.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Less
				+ ConditionCheckerUtil.COMA + "1.0" + ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND
				+ ConditionCheckerUtil.END_CONDITION + "0.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal
				+ ConditionCheckerUtil.COMA + "0.0" + ConditionCheckerUtil.COMA + ConditionCheckerUtil.CLOSE_PARENTHESIS
				+ ConditionCheckerUtil.OR + ConditionCheckerUtil.END_CONDITION + ConditionCheckerUtil.OPEN_PARENTHESIS
				+ "0.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0"
				+ ConditionCheckerUtil.COMA

				+ ConditionCheckerUtil.OR + ConditionCheckerUtil.END_CONDITION + "1.0" + ConditionCheckerUtil.HASH
				+ ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0" + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.CLOSE_PARENTHESIS + ConditionCheckerUtil.CLOSE_PARENTHESIS
				+ ConditionCheckerUtil.AND + ConditionCheckerUtil.END_CONDITION + ConditionCheckerUtil.OPEN_PARENTHESIS
				+ "1.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.GreaterorEqual + ConditionCheckerUtil.COMA
				+ "1.0" + ConditionCheckerUtil.COMA + ConditionCheckerUtil.CLOSE_PARENTHESIS);
		assertTrue(ConditionCheckerUtil.evalCond(conditionDto));

		conditionDto = new ConditionDto();
		conditionDto.setCondition(ConditionCheckerUtil.OPEN_PARENTHESIS

				+ ConditionCheckerUtil.OPEN_PARENTHESIS + "3.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Less
				+ ConditionCheckerUtil.COMA + "1.0" + ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND
				+ ConditionCheckerUtil.END_CONDITION + "0.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal
				+ ConditionCheckerUtil.COMA + "0.0" + ConditionCheckerUtil.COMA + ConditionCheckerUtil.CLOSE_PARENTHESIS
				+ ConditionCheckerUtil.OR + ConditionCheckerUtil.END_CONDITION + ConditionCheckerUtil.OPEN_PARENTHESIS
				+ "0.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0"
				+ ConditionCheckerUtil.COMA

				+ ConditionCheckerUtil.OR + ConditionCheckerUtil.END_CONDITION + "1.0" + ConditionCheckerUtil.HASH
				+ ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0" + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.CLOSE_PARENTHESIS + ConditionCheckerUtil.CLOSE_PARENTHESIS
				+ ConditionCheckerUtil.AND + ConditionCheckerUtil.END_CONDITION + ConditionCheckerUtil.OPEN_PARENTHESIS
				+ "0.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.GreaterorEqual + ConditionCheckerUtil.COMA
				+ "1.0" + ConditionCheckerUtil.COMA + ConditionCheckerUtil.CLOSE_PARENTHESIS);
		assertFalse(ConditionCheckerUtil.evalCond(conditionDto));

		conditionDto = new ConditionDto();
		conditionDto.setCondition(ConditionCheckerUtil.OPEN_PARENTHESIS + ConditionCheckerUtil.OPEN_PARENTHESIS

				+ "0.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Less + ConditionCheckerUtil.COMA + "1.0"
				+ ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND + ConditionCheckerUtil.END_CONDITION + "0.0"
				+ ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0"
				+ ConditionCheckerUtil.COMA + ConditionCheckerUtil.CLOSE_PARENTHESIS + ConditionCheckerUtil.AND
				+ ConditionCheckerUtil.END_CONDITION + ConditionCheckerUtil.OPEN_PARENTHESIS + "0.0"
				+ ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0"
				+ ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND + ConditionCheckerUtil.END_CONDITION + "1.0"
				+ ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0"
				+ ConditionCheckerUtil.COMA + ConditionCheckerUtil.CLOSE_PARENTHESIS + ConditionCheckerUtil.AND
				+ ConditionCheckerUtil.END_CONDITION + ConditionCheckerUtil.OPEN_PARENTHESIS + "1.0"
				+ ConditionCheckerUtil.HASH + ConditionCheckerUtil.GreaterorEqual + ConditionCheckerUtil.COMA + "1.0"
				+ ConditionCheckerUtil.COMA + ConditionCheckerUtil.CLOSE_PARENTHESIS
				+ ConditionCheckerUtil.CLOSE_PARENTHESIS + ConditionCheckerUtil.OR + ConditionCheckerUtil.END_CONDITION
				+ ConditionCheckerUtil.OPEN_PARENTHESIS + "3.0" + ConditionCheckerUtil.HASH
				+ ConditionCheckerUtil.LessorEqual + ConditionCheckerUtil.COMA + "2.0" + ConditionCheckerUtil.COMA
				+ ConditionCheckerUtil.CLOSE_PARENTHESIS);
		// cond===((0.0#<,1.0,&&;0.0#=,0.0,)&&;(0.0#=,0.0,&&;1.0#=,0.0,)&&;(1.0#>=,1.0,))||;(3.0#<=,2.0,)
		assertFalse(ConditionCheckerUtil.evalCond(conditionDto));

		conditionDto = new ConditionDto();
		//
		conditionDto.setCondition("0.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Less
				+ ConditionCheckerUtil.COMA + "1.0" + ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND
				+ ConditionCheckerUtil.END_CONDITION + ConditionCheckerUtil.OPEN_PARENTHESIS + "0.0"
				+ ConditionCheckerUtil.HASH + ConditionCheckerUtil.Less + ConditionCheckerUtil.COMA + "1.0"
				+ ConditionCheckerUtil.COMA + ConditionCheckerUtil.OR + ConditionCheckerUtil.END_CONDITION + "1.0"
				+ ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0"
				+ ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND + ConditionCheckerUtil.END_CONDITION + "0.0"
				+ ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0"
				+ ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND + ConditionCheckerUtil.END_CONDITION + "1.0"
				+ ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "1.0"
				+ ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND + ConditionCheckerUtil.END_CONDITION + "1.0"
				+ ConditionCheckerUtil.HASH + ConditionCheckerUtil.GreaterorEqual + ConditionCheckerUtil.COMA + "1.0"
				+ ConditionCheckerUtil.COMA + ConditionCheckerUtil.CLOSE_PARENTHESIS);
		assertTrue(ConditionCheckerUtil.evalCond(conditionDto));

		conditionDto = new ConditionDto();
		//
		conditionDto.setCondition("1.0" + ConditionCheckerUtil.HASH + ConditionCheckerUtil.Less
				+ ConditionCheckerUtil.COMA + "1.0" + ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND
				+ ConditionCheckerUtil.END_CONDITION + ConditionCheckerUtil.OPEN_PARENTHESIS + "0.0"
				+ ConditionCheckerUtil.HASH + ConditionCheckerUtil.Less + ConditionCheckerUtil.COMA + "1.0"
				+ ConditionCheckerUtil.COMA + ConditionCheckerUtil.OR + ConditionCheckerUtil.END_CONDITION + "1.0"
				+ ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0"
				+ ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND + ConditionCheckerUtil.END_CONDITION + "0.0"
				+ ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "0.0"
				+ ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND + ConditionCheckerUtil.END_CONDITION + "1.0"
				+ ConditionCheckerUtil.HASH + ConditionCheckerUtil.Equal + ConditionCheckerUtil.COMA + "1.0"
				+ ConditionCheckerUtil.COMA + ConditionCheckerUtil.AND + ConditionCheckerUtil.END_CONDITION + "1.0"
				+ ConditionCheckerUtil.HASH + ConditionCheckerUtil.GreaterorEqual + ConditionCheckerUtil.COMA + "1.0"
				+ ConditionCheckerUtil.COMA + ConditionCheckerUtil.CLOSE_PARENTHESIS);
		assertFalse(ConditionCheckerUtil.evalCond(conditionDto));

	}

}
