package CorbaApp2;

/**
* CorbaApp2/NoEnoughItemsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CorbaApp2.idl
* sobota, 24 czerwca 2017 13:56:40 CEST
*/

public final class NoEnoughItemsHolder implements org.omg.CORBA.portable.Streamable
{
  public CorbaApp2.NoEnoughItems value = null;

  public NoEnoughItemsHolder ()
  {
  }

  public NoEnoughItemsHolder (CorbaApp2.NoEnoughItems initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = CorbaApp2.NoEnoughItemsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    CorbaApp2.NoEnoughItemsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return CorbaApp2.NoEnoughItemsHelper.type ();
  }

}
