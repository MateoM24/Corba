package CorbaApp2;


/**
* CorbaApp2/StockIsEmpty.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CorbaApp2.idl
* sobota, 24 czerwca 2017 13:56:40 CEST
*/

public final class StockIsEmpty extends org.omg.CORBA.UserException
{
  public String reason = null;

  public StockIsEmpty ()
  {
    super(StockIsEmptyHelper.id());
  } // ctor

  public StockIsEmpty (String _reason)
  {
    super(StockIsEmptyHelper.id());
    reason = _reason;
  } // ctor


  public StockIsEmpty (String $reason, String _reason)
  {
    super(StockIsEmptyHelper.id() + "  " + $reason);
    reason = _reason;
  } // ctor

} // class StockIsEmpty
