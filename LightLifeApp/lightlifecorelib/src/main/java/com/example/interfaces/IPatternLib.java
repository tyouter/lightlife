package com.example.interfaces;

/** A pattern library interface
 * Created by Administrator on 2016/12/5.
 */
public interface IPatternLib {

    /** if there is a same pattern
     * @param pattern pattern to compare
     * @return true has, false doesn't
     */
    boolean hasPattern(IPattern pattern);

    /** add pattern to library
     * @param pattern pattern to add
     */
    void addPattern(IPattern pattern);
}
