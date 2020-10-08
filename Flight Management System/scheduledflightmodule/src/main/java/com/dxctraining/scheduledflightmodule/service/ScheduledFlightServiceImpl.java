package com.dxctraining.scheduledflightmodule.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dxctraining.scheduledflightmodule.dao.IScheduledFlightDao;
import com.dxctraining.scheduledflightmodule.entities.ScheduledFlight;
import com.dxctraining.scheduledflightmodule.exceptions.InvalidArgumentException;
import com.dxctraining.scheduledflightmodule.exceptions.ScheduledFlightNotFoundException;

@Transactional
@Service
public class ScheduledFlightServiceImpl implements IScheduledFlightService {
	
	@Autowired
	private IScheduledFlightDao dao;

	@Override
	public ScheduledFlight save(ScheduledFlight scheduledFlight) {
		validate(scheduledFlight);
		scheduledFlight = dao.save(scheduledFlight);
		return scheduledFlight;
	}
	

	@Override
	public ScheduledFlight findBySfId(BigInteger sfId) {
		Optional<ScheduledFlight> optional = dao.findById(sfId);
		if(!optional.isPresent()) {
			throw new ScheduledFlightNotFoundException("scheduled flight not found for entered sfId,enter valid sfId");
		}
		ScheduledFlight scheduledFlight = optional.get();
		return scheduledFlight;
	}

	private void validate(ScheduledFlight scheduledFlight) {
		if(scheduledFlight == null) {
			throw new InvalidArgumentException("scheduledFlight should not be null");
		}
		
	}

	@Override
	public List<ScheduledFlight> allScheduledFlights() {
		List<ScheduledFlight> list = dao.findAll();
		return list;
	}


	@Override
	public void deleteScheduledFlight(BigInteger sfId) {
		findBySfId(sfId);
		dao.deleteById(sfId);
	}

}
