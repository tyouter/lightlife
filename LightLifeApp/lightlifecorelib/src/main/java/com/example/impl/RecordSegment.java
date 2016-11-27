package com.example.impl;

import com.example.consts.Status;
import com.example.interfaces.IRecordSegment;

public class RecordSegment implements IRecordSegment {
	/**
	 * Outputs, usually represent the documentation absolute path
	 */
	private final String outputs;

	/**
	 * A segment time cost
	 */
	private final Double timeSpend;
	/**
	 * Segment status
	 */
	private final Status status;
	/**
	 * Describe this segment
	 */
	private final String description;

	public RecordSegment(String outputs, String description, Double timeSpend, Status status) {
		this.outputs = outputs;
		this.timeSpend = timeSpend;
		this.status = status;
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see interfaces.IRecordSegment#getOutputs()
	 */
	public String getOutputs() {
		return outputs;
	}

	/* (non-Javadoc)
	 * @see interfaces.IRecordSegment#getTimeSpend()
	 */
	public Double getTimeSpend() {
		return timeSpend;
	}

	/* (non-Javadoc)
	 * @see interfaces.IRecordSegment#getStatus()
	 */
	public Status getStatus() {
		return status;
	}

	/* (non-Javadoc)
	 * @see interfaces.IRecordSegment#getDescription()
	 */
	public String getDescription() {
		return description;
	}
}
