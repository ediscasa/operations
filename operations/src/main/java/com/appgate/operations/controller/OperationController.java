package com.appgate.operations.controller;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appgate.operations.controller.dto.OperandDto;
import com.appgate.operations.exceptions.OperationWithoutOperandException;
import com.appgate.operations.exceptions.OperationWithoutSessionException;
import com.appgate.operations.service.OperationService;

@RestController
@RequestMapping("operation")
public class OperationController {

	private static final String OPERACION_NO_PERMITIDA = "Operacion no permitida";

	private static final String PETICION_INCOMPLETA = "Peticion incompleta";
	
	@Autowired
	private OperationService operationService;

	@GetMapping("/startSession")
	public ResponseEntity<String> startSession() {
		return new ResponseEntity<String>(operationService.startOperation(), HttpStatus.CREATED);
	}

	@PostMapping("/addOperand")
	public ResponseEntity<String> inOperandValue(@RequestBody OperandDto operandDto) {

		if (Objects.isNull(operandDto) || Objects.isNull(operandDto.getIdSession())
				|| Objects.isNull(operandDto.getOperand())) {
			return new ResponseEntity<String>(PETICION_INCOMPLETA, HttpStatus.BAD_REQUEST);
		}

		try {
			operationService.addOperand(operandDto.getIdSession(), operandDto.getOperand());
		} catch (OperationWithoutSessionException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@GetMapping("/performOperation")
	public ResponseEntity<?> sumOperate(@RequestParam String idSession, @RequestParam String operation){
	
		BigDecimal result;
		try {

		    switch (operation) {
		        case "ADD":
					result = operationService.sumOperation(idSession);
		            break;
		        case "MULTIPLY":
		            result = operationService.multiplicationOperation(idSession);
		            break;
		        case "DIVIDE":
		            result = operationService.divisionOperation(idSession);
		            break;
		        case "SUBTRACT":
		            result = operationService.subtractionOperation(idSession);
		            break;
		        case "POW":
		            result = operationService.exponentiationOperation(idSession);
		            break;
		        default:
		        	return new ResponseEntity<String>(OPERACION_NO_PERMITIDA, HttpStatus.BAD_REQUEST);
		    }
		} catch (OperationWithoutSessionException | OperationWithoutOperandException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	    
	    return new ResponseEntity<BigDecimal>(result, HttpStatus.OK);
		
	}
	

}
