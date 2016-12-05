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
	void addSegment(IRecordSegment segment);
	List<IRecordSegment> getSegments();
}
