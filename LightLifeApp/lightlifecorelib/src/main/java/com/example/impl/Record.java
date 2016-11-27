/**
 * 
 */
package com.example.impl;

import com.example.interfaces.IRecord;
import com.example.interfaces.IRecordSegment;

import java.util.ArrayList;
import java.util.List;

/**Implement of IRecord
 * @author Administrator
 *
 */
public class Record implements IRecord {
	List<IRecordSegment> mSegments;
	public Record() {
		mSegments = new ArrayList<>();
	}
	public void addSegment(IRecordSegment segment) {
		mSegments.add(segment);
	}
	public List<IRecordSegment> getSegments() {
		return mSegments;
	}
}
