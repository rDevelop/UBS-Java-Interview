package data.columns;

/**
 * Enumerated class of <tt>Columns</tt><br>
 * Used by the {@link data.Data} class to match field indices with ordinals.
 */
public enum Columns {
    COMPANY_CODE, /** The company code */
    ACCOUNT, /** Account desription */
    CITY, /** City will be used as country if country is null */
    COUNTRY, /** Can be null, but will be part of the key */
    CREDIT_RATING, /** The second half of the key */
    CURRENCY, /** Currency is used for converting to Euro */
    AMOUNT /** The amount is accumulative amount */
}