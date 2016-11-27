/**
 * 
 */
package com.example.interfaces;

import java.util.List;

/**Interface of record, define a record saving information of user
 * @author Administrator
 *
 */
public interface IRecord {
	public void addSegment(IRecordSegment segment);
	public List<IRecordSegment> getSegments();
}
