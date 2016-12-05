package com.example.interfaces;

/** A handler to do with communication
 * Created by Administrator on 2016/12/5.
 */
public interface IReplyHandler {

    /** Accept command, and return result
     * @return result of command
     */
    IResult accept();

    /** Recommendation of unclear message
     * @return Another reply handler
     */
    IReplyHandler recommendation();

    /** Reject apply
     * @return return result
     */
    IResult rejected();

    /** Return message for interaction
     * @return message for interaction
     */
    String getMessage();
}
