package CorbaApp2;


/**
* CorbaApp2/OrdersHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CorbaApp2.idl
* sobota, 24 czerwca 2017 13:56:40 CEST
*/

abstract public class OrdersHelper
{
  private static String  _id = "IDL:CorbaApp2/Orders:1.0";

  public static void insert (org.omg.CORBA.Any a, CorbaApp2.OrderItem[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static CorbaApp2.OrderItem[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = CorbaApp2.OrderItemHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (50, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (CorbaApp2.OrdersHelper.id (), "Orders", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static CorbaApp2.OrderItem[] read (org.omg.CORBA.portable.InputStream istream)
  {
    CorbaApp2.OrderItem value[] = null;
    int _len0 = istream.read_long ();
    if (_len0 > (50))
      throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    value = new CorbaApp2.OrderItem[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = CorbaApp2.OrderItemHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, CorbaApp2.OrderItem[] value)
  {
    if (value.length > (50))
      throw new org.omg.CORBA.MARSHAL (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      CorbaApp2.OrderItemHelper.write (ostream, value[_i0]);
  }

}
