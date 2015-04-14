package compilador;

import java_cup.runtime.*;
import ast.*;


public class parser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public parser() {super();}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\105\000\002\002\003\000\002\002\004\000\002\003" +
    "\004\000\002\003\003\000\002\004\005\000\002\004\004" +
    "\000\002\005\004\000\002\005\003\000\002\006\012\000" +
    "\002\006\011\000\002\007\003\000\002\007\003\000\002" +
    "\010\006\000\002\010\004\000\002\011\003\000\002\011" +
    "\003\000\002\012\005\000\002\012\004\000\002\013\003" +
    "\000\002\013\003\000\002\013\003\000\002\013\003\000" +
    "\002\013\003\000\002\013\003\000\002\013\003\000\002" +
    "\013\003\000\002\013\003\000\002\013\003\000\002\014" +
    "\007\000\002\014\011\000\002\015\006\000\002\016\005" +
    "\000\002\017\004\000\002\020\004\000\002\021\004\000" +
    "\002\022\005\000\002\022\003\000\002\023\006\000\002" +
    "\023\003\000\002\024\006\000\002\024\005\000\002\025" +
    "\005\000\002\025\003\000\002\026\014\000\002\027\004" +
    "\000\002\030\005\000\002\030\003\000\002\031\005\000" +
    "\002\031\003\000\002\032\005\000\002\032\005\000\002" +
    "\032\005\000\002\032\005\000\002\032\005\000\002\032" +
    "\005\000\002\032\003\000\002\033\005\000\002\033\005" +
    "\000\002\033\003\000\002\034\005\000\002\034\005\000" +
    "\002\034\003\000\002\035\005\000\002\035\003\000\002" +
    "\035\003\000\002\035\003\000\002\035\003\000\002\035" +
    "\003\000\002\035\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\206\000\012\035\016\042\005\043\004\044\011\001" +
    "\002\000\004\014\ufff2\001\002\000\004\014\ufff3\001\002" +
    "\000\012\035\016\042\005\043\004\044\011\001\002\000" +
    "\012\035\ufffa\042\ufffa\043\ufffa\044\ufffa\001\002\000\004" +
    "\002\ufffe\001\002\000\004\014\ufff6\001\002\000\004\002" +
    "\206\001\002\000\004\014\166\001\002\000\004\002\001" +
    "\001\002\000\004\014\ufff7\001\002\000\030\003\033\004" +
    "\024\007\021\010\040\012\020\013\025\014\026\034\037" +
    "\042\005\043\004\046\022\001\002\000\004\027\uffee\001" +
    "\002\000\004\014\165\001\002\000\004\002\ufffc\001\002" +
    "\000\016\003\061\014\057\015\051\025\064\047\053\050" +
    "\056\001\002\000\004\027\uffec\001\002\000\016\003\061" +
    "\014\057\015\051\025\064\047\053\050\056\001\002\000" +
    "\016\003\061\014\057\015\051\025\064\047\053\050\056" +
    "\001\002\000\006\016\141\025\120\001\002\000\004\014" +
    "\145\001\002\000\004\027\uffe9\001\002\000\004\027\uffe7" +
    "\001\002\000\030\003\033\004\024\007\143\010\040\012" +
    "\020\013\025\014\026\034\037\042\005\043\004\046\022" +
    "\001\002\000\004\027\uffe6\001\002\000\004\027\uffef\001" +
    "\002\000\004\027\uffe8\001\002\000\004\027\uffeb\001\002" +
    "\000\004\025\127\001\002\000\026\003\033\004\024\010" +
    "\040\012\020\013\025\014\026\034\037\042\005\043\004" +
    "\046\022\001\002\000\004\027\uffea\001\002\000\004\027" +
    "\044\001\002\000\004\027\uffed\001\002\000\034\003\ufff0" +
    "\004\ufff0\006\ufff0\007\ufff0\010\ufff0\011\ufff0\012\ufff0\013" +
    "\ufff0\014\ufff0\034\ufff0\042\ufff0\043\ufff0\046\ufff0\001\002" +
    "\000\030\003\033\004\024\010\040\011\046\012\020\013" +
    "\025\014\026\034\037\042\005\043\004\046\022\001\002" +
    "\000\016\003\061\014\057\015\051\025\064\047\053\050" +
    "\056\001\002\000\004\027\050\001\002\000\034\003\ufff1" +
    "\004\ufff1\006\ufff1\007\ufff1\010\ufff1\011\ufff1\012\ufff1\013" +
    "\ufff1\014\ufff1\034\ufff1\042\ufff1\043\ufff1\046\ufff1\001\002" +
    "\000\042\005\uffc1\017\uffc1\020\uffc1\021\uffc1\022\uffc1\023" +
    "\uffc1\024\uffc1\026\uffc1\027\uffc1\030\uffc1\031\uffc1\032\uffc1" +
    "\033\uffc1\036\uffc1\037\uffc1\045\uffc1\001\002\000\042\005" +
    "\uffc7\017\uffc7\020\uffc7\021\uffc7\022\uffc7\023\107\024\106" +
    "\026\uffc7\027\uffc7\030\uffc7\031\uffc7\032\uffc7\033\uffc7\036" +
    "\uffc7\037\uffc7\045\uffc7\001\002\000\042\005\uffbf\017\uffbf" +
    "\020\uffbf\021\uffbf\022\uffbf\023\uffbf\024\uffbf\026\uffbf\027" +
    "\uffbf\030\uffbf\031\uffbf\032\uffbf\033\uffbf\036\uffbf\037\uffbf" +
    "\045\uffbf\001\002\000\016\005\uffd3\026\uffd3\027\uffd3\036" +
    "\072\037\uffd3\045\uffd3\001\002\000\006\027\uffe3\037\070" +
    "\001\002\000\042\005\uffbe\017\uffbe\020\uffbe\021\uffbe\022" +
    "\uffbe\023\uffbe\024\uffbe\026\uffbe\027\uffbe\030\uffbe\031\uffbe" +
    "\032\uffbe\033\uffbe\036\uffbe\037\uffbe\045\uffbe\001\002\000" +
    "\044\005\uffc0\017\uffc0\020\uffc0\021\uffc0\022\uffc0\023\uffc0" +
    "\024\uffc0\025\120\026\uffc0\027\uffc0\030\uffc0\031\uffc0\032" +
    "\uffc0\033\uffc0\036\uffc0\037\uffc0\045\uffc0\001\002\000\042" +
    "\005\uffc4\017\uffc4\020\uffc4\021\uffc4\022\uffc4\023\uffc4\024" +
    "\uffc4\026\uffc4\027\uffc4\030\uffc4\031\uffc4\032\uffc4\033\uffc4" +
    "\036\uffc4\037\uffc4\045\uffc4\001\002\000\042\005\uffbd\017" +
    "\uffbd\020\uffbd\021\uffbd\022\uffbd\023\uffbd\024\uffbd\026\uffbd" +
    "\027\uffbd\030\uffbd\031\uffbd\032\uffbd\033\uffbd\036\uffbd\037" +
    "\uffbd\045\uffbd\001\002\000\042\005\uffc2\017\uffc2\020\uffc2" +
    "\021\uffc2\022\uffc2\023\uffc2\024\uffc2\026\uffc2\027\uffc2\030" +
    "\uffc2\031\uffc2\032\uffc2\033\uffc2\036\uffc2\037\uffc2\045\uffc2" +
    "\001\002\000\036\005\uffca\017\075\020\074\021\077\022" +
    "\102\026\uffca\027\uffca\030\076\031\103\032\101\033\100" +
    "\036\uffca\037\uffca\045\uffca\001\002\000\016\003\061\014" +
    "\057\015\051\025\064\047\053\050\056\001\002\000\016" +
    "\005\uffd1\026\uffd1\027\uffd1\036\uffd1\037\uffd1\045\uffd1\001" +
    "\002\000\006\026\067\037\070\001\002\000\042\005\uffc3" +
    "\017\uffc3\020\uffc3\021\uffc3\022\uffc3\023\uffc3\024\uffc3\026" +
    "\uffc3\027\uffc3\030\uffc3\031\uffc3\032\uffc3\033\uffc3\036\uffc3" +
    "\037\uffc3\045\uffc3\001\002\000\016\003\061\014\057\015" +
    "\051\025\064\047\053\050\056\001\002\000\016\005\uffd4" +
    "\026\uffd4\027\uffd4\036\072\037\uffd4\045\uffd4\001\002\000" +
    "\016\003\061\014\057\015\051\025\064\047\053\050\056" +
    "\001\002\000\016\005\uffd2\026\uffd2\027\uffd2\036\uffd2\037" +
    "\uffd2\045\uffd2\001\002\000\016\003\061\014\057\015\051" +
    "\025\064\047\053\050\056\001\002\000\016\003\061\014" +
    "\057\015\051\025\064\047\053\050\056\001\002\000\016" +
    "\003\061\014\057\015\051\025\064\047\053\050\056\001" +
    "\002\000\016\003\061\014\057\015\051\025\064\047\053" +
    "\050\056\001\002\000\016\003\061\014\057\015\051\025" +
    "\064\047\053\050\056\001\002\000\016\003\061\014\057" +
    "\015\051\025\064\047\053\050\056\001\002\000\016\003" +
    "\061\014\057\015\051\025\064\047\053\050\056\001\002" +
    "\000\016\003\061\014\057\015\051\025\064\047\053\050" +
    "\056\001\002\000\022\005\uffcd\021\077\022\102\026\uffcd" +
    "\027\uffcd\036\uffcd\037\uffcd\045\uffcd\001\002\000\042\005" +
    "\uffc8\017\uffc8\020\uffc8\021\uffc8\022\uffc8\023\107\024\106" +
    "\026\uffc8\027\uffc8\030\uffc8\031\uffc8\032\uffc8\033\uffc8\036" +
    "\uffc8\037\uffc8\045\uffc8\001\002\000\016\003\061\014\057" +
    "\015\051\025\064\047\053\050\056\001\002\000\016\003" +
    "\061\014\057\015\051\025\064\047\053\050\056\001\002" +
    "\000\042\005\uffc6\017\uffc6\020\uffc6\021\uffc6\022\uffc6\023" +
    "\uffc6\024\uffc6\026\uffc6\027\uffc6\030\uffc6\031\uffc6\032\uffc6" +
    "\033\uffc6\036\uffc6\037\uffc6\045\uffc6\001\002\000\042\005" +
    "\uffc5\017\uffc5\020\uffc5\021\uffc5\022\uffc5\023\uffc5\024\uffc5" +
    "\026\uffc5\027\uffc5\030\uffc5\031\uffc5\032\uffc5\033\uffc5\036" +
    "\uffc5\037\uffc5\045\uffc5\001\002\000\022\005\uffcc\021\077" +
    "\022\102\026\uffcc\027\uffcc\036\uffcc\037\uffcc\045\uffcc\001" +
    "\002\000\022\005\uffcb\021\077\022\102\026\uffcb\027\uffcb" +
    "\036\uffcb\037\uffcb\045\uffcb\001\002\000\042\005\uffc9\017" +
    "\uffc9\020\uffc9\021\uffc9\022\uffc9\023\107\024\106\026\uffc9" +
    "\027\uffc9\030\uffc9\031\uffc9\032\uffc9\033\uffc9\036\uffc9\037" +
    "\uffc9\045\uffc9\001\002\000\022\005\uffce\021\077\022\102" +
    "\026\uffce\027\uffce\036\uffce\037\uffce\045\uffce\001\002\000" +
    "\022\005\uffcf\021\077\022\102\026\uffcf\027\uffcf\036\uffcf" +
    "\037\uffcf\045\uffcf\001\002\000\022\005\uffd0\021\077\022" +
    "\102\026\uffd0\027\uffd0\036\uffd0\037\uffd0\045\uffd0\001\002" +
    "\000\020\003\061\014\057\015\051\025\064\026\123\047" +
    "\053\050\056\001\002\000\010\026\uffd7\037\070\045\uffd7" +
    "\001\002\000\006\026\125\045\124\001\002\000\042\005" +
    "\uffd9\017\uffd9\020\uffd9\021\uffd9\022\uffd9\023\uffd9\024\uffd9" +
    "\026\uffd9\027\uffd9\030\uffd9\031\uffd9\032\uffd9\033\uffd9\036" +
    "\uffd9\037\uffd9\045\uffd9\001\002\000\016\003\061\014\057" +
    "\015\051\025\064\047\053\050\056\001\002\000\042\005" +
    "\uffda\017\uffda\020\uffda\021\uffda\022\uffda\023\uffda\024\uffda" +
    "\026\uffda\027\uffda\030\uffda\031\uffda\032\uffda\033\uffda\036" +
    "\uffda\037\uffda\045\uffda\001\002\000\010\026\uffd8\037\070" +
    "\045\uffd8\001\002\000\004\014\130\001\002\000\004\016" +
    "\141\001\002\000\004\027\132\001\002\000\016\003\061" +
    "\014\057\015\051\025\064\047\053\050\056\001\002\000" +
    "\006\027\134\037\070\001\002\000\004\014\130\001\002" +
    "\000\004\026\136\001\002\000\026\003\033\004\024\010" +
    "\040\012\020\013\025\014\026\034\037\042\005\043\004" +
    "\046\022\001\002\000\030\003\033\004\024\007\140\010" +
    "\040\012\020\013\025\014\026\034\037\042\005\043\004" +
    "\046\022\001\002\000\004\027\uffd6\001\002\000\016\003" +
    "\061\014\057\015\051\025\064\047\053\050\056\001\002" +
    "\000\010\026\uffe2\027\uffe2\037\070\001\002\000\004\002" +
    "\ufffd\001\002\000\006\027\uffdf\045\152\001\002\000\010" +
    "\027\uffdb\040\147\045\uffdb\001\002\000\006\027\uffdd\045" +
    "\uffdd\001\002\000\004\015\150\001\002\000\004\041\151" +
    "\001\002\000\006\027\uffdc\045\uffdc\001\002\000\004\014" +
    "\145\001\002\000\006\027\uffde\045\uffde\001\002\000\006" +
    "\027\uffe0\037\070\001\002\000\006\005\156\037\070\001" +
    "\002\000\026\003\033\004\024\010\040\012\020\013\025" +
    "\014\026\034\037\042\005\043\004\046\022\001\002\000" +
    "\032\003\033\004\024\006\160\007\161\010\040\012\020" +
    "\013\025\014\026\034\037\042\005\043\004\046\022\001" +
    "\002\000\026\003\033\004\024\010\040\012\020\013\025" +
    "\014\026\034\037\042\005\043\004\046\022\001\002\000" +
    "\004\027\uffe5\001\002\000\030\003\033\004\024\007\163" +
    "\010\040\012\020\013\025\014\026\034\037\042\005\043" +
    "\004\046\022\001\002\000\004\027\uffe4\001\002\000\006" +
    "\027\uffd5\037\070\001\002\000\004\027\uffe1\001\002\000" +
    "\004\025\167\001\002\000\010\026\172\042\005\043\004" +
    "\001\002\000\004\014\205\001\002\000\006\026\177\045" +
    "\176\001\002\000\004\035\173\001\002\000\026\003\033" +
    "\004\024\010\040\012\020\013\025\014\026\034\037\042" +
    "\005\043\004\046\022\001\002\000\030\003\033\004\024" +
    "\007\175\010\040\012\020\013\025\014\026\034\037\042" +
    "\005\043\004\046\022\001\002\000\012\035\ufff8\042\ufff8" +
    "\043\ufff8\044\ufff8\001\002\000\006\042\005\043\004\001" +
    "\002\000\004\035\200\001\002\000\026\003\033\004\024" +
    "\010\040\012\020\013\025\014\026\034\037\042\005\043" +
    "\004\046\022\001\002\000\030\003\033\004\024\007\202" +
    "\010\040\012\020\013\025\014\026\034\037\042\005\043" +
    "\004\046\022\001\002\000\012\035\ufff9\042\ufff9\043\ufff9" +
    "\044\ufff9\001\002\000\004\014\204\001\002\000\006\026" +
    "\ufff5\045\ufff5\001\002\000\006\026\ufff4\045\ufff4\001\002" +
    "\000\004\002\000\001\002\000\004\002\uffff\001\002\000" +
    "\012\035\ufffb\042\ufffb\043\ufffb\044\ufffb\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\206\000\020\002\011\003\013\004\007\005\005\006" +
    "\006\007\012\011\014\001\001\000\002\001\001\000\002" +
    "\001\001\000\012\004\206\006\207\007\012\011\014\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\032\011\026\012\031\013\041\014\033" +
    "\015\016\016\042\017\022\020\035\021\040\024\027\026" +
    "\034\027\030\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\020\024\061\030\163\031\053\032" +
    "\064\033\062\034\051\035\057\001\001\000\002\001\001" +
    "\000\020\024\061\030\154\031\053\032\064\033\062\034" +
    "\051\035\057\001\001\000\020\024\061\030\153\031\053" +
    "\032\064\033\062\034\051\035\057\001\001\000\002\001" +
    "\001\000\006\022\143\023\145\001\001\000\002\001\001" +
    "\000\002\001\001\000\030\011\026\013\046\014\033\015" +
    "\016\016\042\017\022\020\035\021\040\024\027\026\034" +
    "\027\030\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\032" +
    "\011\026\012\044\013\041\014\033\015\016\016\042\017" +
    "\022\020\035\021\040\024\027\026\034\027\030\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\030\011\026\013\046\014\033\015\016" +
    "\016\042\017\022\020\035\021\040\024\027\026\034\027" +
    "\030\001\001\000\020\024\061\030\054\031\053\032\064" +
    "\033\062\034\051\035\057\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\020\024\061\030" +
    "\065\031\053\032\064\033\062\034\051\035\057\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\016\024\061\031\070\032\064\033\062\034\051\035\057" +
    "\001\001\000\002\001\001\000\014\024\061\032\072\033" +
    "\062\034\051\035\057\001\001\000\002\001\001\000\012" +
    "\024\061\033\116\034\051\035\057\001\001\000\012\024" +
    "\061\033\115\034\051\035\057\001\001\000\012\024\061" +
    "\033\114\034\051\035\057\001\001\000\010\024\061\034" +
    "\113\035\057\001\001\000\012\024\061\033\112\034\051" +
    "\035\057\001\001\000\012\024\061\033\111\034\051\035" +
    "\057\001\001\000\010\024\061\034\104\035\057\001\001" +
    "\000\012\024\061\033\103\034\051\035\057\001\001\000" +
    "\002\001\001\000\002\001\001\000\006\024\061\035\110" +
    "\001\001\000\006\024\061\035\107\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\022\024\061\025\121\030\120\031\053" +
    "\032\064\033\062\034\051\035\057\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\020\024\061" +
    "\030\125\031\053\032\064\033\062\034\051\035\057\001" +
    "\001\000\002\001\001\000\002\001\001\000\004\016\130" +
    "\001\001\000\002\001\001\000\002\001\001\000\020\024" +
    "\061\030\132\031\053\032\064\033\062\034\051\035\057" +
    "\001\001\000\002\001\001\000\004\016\134\001\001\000" +
    "\002\001\001\000\032\011\026\012\136\013\041\014\033" +
    "\015\016\016\042\017\022\020\035\021\040\024\027\026" +
    "\034\027\030\001\001\000\030\011\026\013\046\014\033" +
    "\015\016\016\042\017\022\020\035\021\040\024\027\026" +
    "\034\027\030\001\001\000\002\001\001\000\020\024\061" +
    "\030\141\031\053\032\064\033\062\034\051\035\057\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\004\023\152\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\032\011\026\012\156\013\041\014\033\015\016\016\042" +
    "\017\022\020\035\021\040\024\027\026\034\027\030\001" +
    "\001\000\030\011\026\013\046\014\033\015\016\016\042" +
    "\017\022\020\035\021\040\024\027\026\034\027\030\001" +
    "\001\000\032\011\026\012\161\013\041\014\033\015\016" +
    "\016\042\017\022\020\035\021\040\024\027\026\034\027" +
    "\030\001\001\000\002\001\001\000\030\011\026\013\046" +
    "\014\033\015\016\016\042\017\022\020\035\021\040\024" +
    "\027\026\034\027\030\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\006\010" +
    "\170\011\167\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\032\011\026\012\173\013\041\014" +
    "\033\015\016\016\042\017\022\020\035\021\040\024\027" +
    "\026\034\027\030\001\001\000\030\011\026\013\046\014" +
    "\033\015\016\016\042\017\022\020\035\021\040\024\027" +
    "\026\034\027\030\001\001\000\002\001\001\000\004\011" +
    "\202\001\001\000\002\001\001\000\032\011\026\012\200" +
    "\013\041\014\033\015\016\016\042\017\022\020\035\021" +
    "\040\024\027\026\034\027\030\001\001\000\030\011\026" +
    "\013\046\014\033\015\016\016\042\017\022\020\035\021" +
    "\040\024\027\026\034\027\030\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$parser$actions {


	private boolean modoDepuracion=false;
	private NodoBase ast=null;
	private int lineanum=0;
	
	//Debe ser llamada solo despues de un parse(), en caso contrario retornara null siempre;
	public NodoBase getASTroot(){
		return ast;
	}

  private final parser parser;

  /** Constructor */
  CUP$parser$actions(parser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$parser$do_action(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parser$result;

      /* select the action based on the action number */
      switch (CUP$parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 68: // factor ::= error 
            {
              NodoBase RESULT =null;
		
                if(modoDepuracion)
                    System.out.println("\t regla 29g");
                System.out.println("Ocurrió error en cup # 0003");
                RESULT = null;
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("factor",27, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 67: // factor ::= FALSE 
            {
              NodoBase RESULT =null;
		
                if(modoDepuracion)
                    System.out.println("\t regla 29f");
                RESULT = new NodoValor(false, tipoVariable.booleano);
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("factor",27, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 66: // factor ::= TRUE 
            {
              NodoBase RESULT =null;
		
                if(modoDepuracion)
                    System.out.println("\t regla 29e");
                RESULT = new NodoValor(true, tipoVariable.booleano);
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("factor",27, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 65: // factor ::= ID 
            {
              NodoBase RESULT =null;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 29d");
                RESULT = new NodoIdentificador(id);
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("factor",27, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 64: // factor ::= NUM 
            {
              NodoBase RESULT =null;
		Integer nm = (Integer)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 29c");
                RESULT = new NodoValor(nm, tipoVariable.entero);
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("factor",27, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 63: // factor ::= func_call 
            {
              NodoBase RESULT =null;
		NodoBase fc = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 29b");
                RESULT = fc;
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("factor",27, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 62: // factor ::= LPAREN exp RPAREN 
            {
              NodoBase RESULT =null;
		NodoBase ep = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 29a");
                RESULT = ep;
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("factor",27, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 61: // term ::= factor 
            {
              NodoBase RESULT =null;
		NodoBase ft = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 28c");
                RESULT = ft;
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("term",26, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 60: // term ::= term OVER factor 
            {
              NodoBase RESULT =null;
		NodoBase tm = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		NodoBase ft = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 28b");
                RESULT = new NodoOperacion(tm, tipoOp.entre, ft);
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("term",26, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 59: // term ::= term TIMES factor 
            {
              NodoBase RESULT =null;
		NodoBase tm = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		NodoBase ft = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 28a");
                RESULT = new NodoOperacion(tm, tipoOp.por, ft);
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("term",26, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 58: // simple_exp ::= term 
            {
              NodoBase RESULT =null;
		NodoBase tm = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 27c");
                    RESULT = tm;
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("simple_exp",25, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 57: // simple_exp ::= simple_exp MINUS term 
            {
              NodoBase RESULT =null;
		NodoBase se = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		NodoBase tm = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 27b");
                    RESULT = new NodoOperacion(se, tipoOp.menos, tm);
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("simple_exp",25, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 56: // simple_exp ::= simple_exp PLUS term 
            {
              NodoBase RESULT =null;
		NodoBase se = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		NodoBase tm = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 27a");
                    RESULT = new NodoOperacion(se, tipoOp.mas, tm);
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("simple_exp",25, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 55: // cmp_exp ::= simple_exp 
            {
              NodoBase RESULT =null;
		NodoBase se = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 26g");
                RESULT = se;
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("cmp_exp",24, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 54: // cmp_exp ::= simple_exp LE simple_exp 
            {
              NodoBase RESULT =null;
		NodoBase se = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		NodoBase se2 = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 26f");
                RESULT = new NodoOperacion(se, tipoOp.menorigual, se2);
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("cmp_exp",24, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 53: // cmp_exp ::= simple_exp GE simple_exp 
            {
              NodoBase RESULT =null;
		NodoBase se = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		NodoBase se2 = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 26e");
                RESULT = new NodoOperacion(se, tipoOp.mayorigual, se2);
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("cmp_exp",24, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 52: // cmp_exp ::= simple_exp NE simple_exp 
            {
              NodoBase RESULT =null;
		NodoBase se = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		NodoBase se2 = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 26d");
                RESULT = new NodoOperacion(se, tipoOp.diferente, se2);
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("cmp_exp",24, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 51: // cmp_exp ::= simple_exp GT simple_exp 
            {
              NodoBase RESULT =null;
		NodoBase se = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		NodoBase se2 = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 26c");
                RESULT = new NodoOperacion(se, tipoOp.mayor, se2);
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("cmp_exp",24, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 50: // cmp_exp ::= simple_exp EQ simple_exp 
            {
              NodoBase RESULT =null;
		NodoBase se = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		NodoBase se2 = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 26b");
                RESULT = new NodoOperacion(se, tipoOp.igual, se2);
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("cmp_exp",24, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 49: // cmp_exp ::= simple_exp LT simple_exp 
            {
              NodoBase RESULT =null;
		NodoBase se = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		NodoBase se2 = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 26a");
                RESULT = new NodoOperacion(se, tipoOp.menor, se2);
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("cmp_exp",24, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 48: // log_exp ::= cmp_exp 
            {
              NodoBase RESULT =null;
		NodoBase ce = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 25b");
                RESULT = ce;
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("log_exp",23, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 47: // log_exp ::= log_exp AND cmp_exp 
            {
              NodoBase RESULT =null;
		NodoBase le = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		NodoBase ce = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 25a");
                RESULT = new NodoOperacion(le, tipoOp.ylogico, ce);
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("log_exp",23, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 46: // exp ::= log_exp 
            {
              NodoBase RESULT =null;
		NodoBase le = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
            if(modoDepuracion)
                    System.out.println("\t regla 24b");
            RESULT = le;
        
              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",22, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 45: // exp ::= exp OR log_exp 
            {
              NodoBase RESULT =null;
		NodoBase ep = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		NodoBase le = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
            if(modoDepuracion)
                    System.out.println("\t regla 24a");
            RESULT = new NodoOperacion(ep, tipoOp.ologico, le);
        
              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",22, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 44: // return_stmt ::= RETURN exp 
            {
              NodoBase RESULT =null;
		NodoBase ep = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 23");
                RESULT = new NodoReturn(ep);
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("return_stmt",21, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 43: // for_stmt ::= FOR LPAREN assign_stmt SEMI exp SEMI assign_stmt RPAREN stmt_seq END 
            {
              NodoBase RESULT =null;
		NodoBase as = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-7)).value;
		NodoBase ep = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-5)).value;
		NodoBase as2 = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		NodoBase ss = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 22");
                RESULT = new NodoFor(as, ep, as2, ss);
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("for_stmt",20, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 42: // call_args ::= exp 
            {
              NodoBase RESULT =null;
		NodoBase ep = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 21b");
                    RESULT = ep;
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("call_args",19, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 41: // call_args ::= call_args COMMA exp 
            {
              NodoBase RESULT =null;
		NodoBase ca = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		NodoBase ep = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 21a");
                    NodoBase nodoActual = ca;
                    if(nodoActual != null)
                    {
                        while(nodoActual.TieneHermano())
                        {
                            nodoActual = nodoActual.getHermanoDerecha();
                        }
                        nodoActual.setHermanoDerecha(ep);
                        RESULT = ca;
                    }
                    else
                    {
                        RESULT = ep;
                    }
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("call_args",19, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 40: // func_call ::= ID LPAREN RPAREN 
            {
              NodoBase RESULT =null;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 20b");
                    RESULT = new NodoLlamada(id);
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("func_call",18, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 39: // func_call ::= ID LPAREN call_args RPAREN 
            {
              NodoBase RESULT =null;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		NodoBase ca = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 20a");
                    RESULT = new NodoLlamada(id, ca);
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("func_call",18, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 38: // decl_name ::= ID 
            {
              NodoBase RESULT =null;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 19b");
                    RESULT = new NodoVariable(id);
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("decl_name",17, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 37: // decl_name ::= ID LSB NUM RSB 
            {
              NodoBase RESULT =null;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		Integer nm = (Integer)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 19a");                    
                    RESULT = new NodoVariable(id, nm);
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("decl_name",17, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 36: // decl_var ::= decl_name 
            {
              NodoBase RESULT =null;
		NodoBase dn = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 18");
                    RESULT = dn;
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("decl_var",16, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 35: // decl_var ::= decl_var COMMA decl_name 
            {
              NodoBase RESULT =null;
		NodoBase dv = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		NodoBase dn = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 17");
                    NodoBase nodoActual = dv;
                    if(dv != null)
                    {
                        while(nodoActual.TieneHermano())
                        {
                            nodoActual = nodoActual.getHermanoDerecha();
                        }
                        nodoActual.setHermanoDerecha(dn);
                        RESULT = dv;
                    }
                    else
                    {
                        RESULT = dn;
                    }
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("decl_var",16, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 34: // decl_stmt ::= var_type decl_var 
            {
              NodoBase RESULT =null;
		tipoVariable vt = (tipoVariable)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		NodoBase dv = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 16");                
                RESULT = new NodoDeclaracion(dv, vt);
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("decl_stmt",15, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 33: // write_stmt ::= WRITE exp 
            {
              NodoBase RESULT =null;
		NodoBase ep = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 15");
                RESULT = new NodoEscribir(ep);
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("write_stmt",14, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 32: // read_stmt ::= READ ID 
            {
              NodoBase RESULT =null;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 14");                
                RESULT = new NodoLeer(id);                
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("read_stmt",13, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 31: // assign_stmt ::= ID ASSIGN exp 
            {
              NodoBase RESULT =null;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		NodoBase ep = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 13");
                RESULT = new NodoAsignacion(id, ep);                
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("assign_stmt",12, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 30: // repeat_stmt ::= REPEAT stmt_seq UNTIL exp 
            {
              NodoBase RESULT =null;
		NodoBase ss = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		NodoBase ep = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 12");
                RESULT = new NodoRepeat(ss, ep);
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("repeat_stmt",11, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 29: // if_stmt ::= IF exp THEN stmt_seq ELSE stmt_seq END 
            {
              NodoBase RESULT =null;
		NodoBase ep = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-5)).value;
		NodoBase ss = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		NodoBase ss2 = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 11b");
                    RESULT = new NodoIf(ep, ss, ss2);
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("if_stmt",10, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 28: // if_stmt ::= IF exp THEN stmt_seq END 
            {
              NodoBase RESULT =null;
		NodoBase ep = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		NodoBase ss = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 11a");
                    RESULT = new NodoIf(ep, ss);
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("if_stmt",10, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27: // stmt ::= error 
            {
              NodoBase RESULT =null;
		
                if(modoDepuracion)
                        System.out.println("\t regla 10j");
                System.out.println("Ocurrió error en cup # 0004");
                RESULT = null;
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmt",9, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // stmt ::= return_stmt 
            {
              NodoBase RESULT =null;
		NodoBase rs = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                        System.out.println("\t regla 10i");
                RESULT = rs;
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmt",9, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // stmt ::= for_stmt 
            {
              NodoBase RESULT =null;
		NodoBase fs = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                        System.out.println("\t regla 10h");
                RESULT = fs;
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmt",9, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // stmt ::= func_call 
            {
              NodoBase RESULT =null;
		NodoBase fc = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                        System.out.println("\t regla 10g");
                RESULT = fc;
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmt",9, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // stmt ::= decl_stmt 
            {
              NodoBase RESULT =null;
		NodoBase ds = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                        System.out.println("\t regla 10f");
                RESULT = ds;
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmt",9, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // stmt ::= write_stmt 
            {
              NodoBase RESULT =null;
		NodoBase ws = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                        System.out.println("\t regla 10e");
                RESULT = ws;
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmt",9, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // stmt ::= read_stmt 
            {
              NodoBase RESULT =null;
		NodoBase rs = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                        System.out.println("\t regla 10d");
                RESULT = rs;
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmt",9, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // stmt ::= assign_stmt 
            {
              NodoBase RESULT =null;
		NodoBase as = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                        System.out.println("\t regla 10c");
                RESULT = as;
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmt",9, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // stmt ::= repeat_stmt 
            {
              NodoBase RESULT =null;
		NodoBase rs = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                        System.out.println("\t regla 10b");
                RESULT = rs;
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmt",9, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // stmt ::= if_stmt 
            {
              NodoBase RESULT =null;
		NodoBase is = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                if(modoDepuracion)
                        System.out.println("\t regla 10a");
                RESULT = is;
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmt",9, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // stmt_seq ::= stmt SEMI 
            {
              NodoBase RESULT =null;
		NodoBase sm = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 9b");
                    RESULT = sm;
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmt_seq",8, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // stmt_seq ::= stmt_seq stmt SEMI 
            {
              NodoBase RESULT =null;
		NodoBase ss = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		NodoBase sm = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 9a");
                    NodoBase nodoActual = ss;
                    if(nodoActual != null)
                    {
                        while(nodoActual.TieneHermano())
                        {
                            nodoActual = nodoActual.getHermanoDerecha();
                        }
                        nodoActual.setHermanoDerecha(sm);
                        RESULT = ss;
                    }
                    else
                    {
                        RESULT = sm;
                    }
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmt_seq",8, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // var_type ::= INT 
            {
              tipoVariable RESULT =null;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 8b");
                    RESULT = tipoVariable.entero;
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("var_type",7, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // var_type ::= BOOL 
            {
              tipoVariable RESULT =null;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 8a");
                    RESULT = tipoVariable.booleano;
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("var_type",7, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // func_args ::= var_type ID 
            {
              NodoBase RESULT =null;
		tipoVariable vt = (tipoVariable)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 7b");                    
                    RESULT = new NodoDeclaracion(new NodoVariable(id), vt);
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("func_args",6, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // func_args ::= func_args COMMA var_type ID 
            {
              NodoBase RESULT =null;
		NodoBase fa = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		tipoVariable vt = (tipoVariable)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 7a");
                    NodoBase nodoActual = fa;
                    NodoDeclaracion nodoSiguiente = new NodoDeclaracion(new NodoVariable(id), vt);
                    if(nodoActual != null)
                    {
                        while(nodoActual.TieneHermano())
                        {
                            nodoActual = nodoActual.getHermanoDerecha();
                        }                        
                        nodoActual.setHermanoDerecha(nodoSiguiente);
                        RESULT = fa;
                    }
                    else
                    {
                        RESULT = nodoSiguiente;
                    }
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("func_args",6, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // func_type ::= VOID 
            {
              tipoVariable RESULT =null;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 6b");
                    RESULT = tipoVariable.vacio;
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("func_type",5, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // func_type ::= var_type 
            {
              tipoVariable RESULT =null;
		tipoVariable vt = (tipoVariable)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 6a");
                    RESULT = vt;
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("func_type",5, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // func ::= func_type ID LPAREN RPAREN BEGIN stmt_seq END 
            {
              NodoBase RESULT =null;
		tipoVariable ft = (tipoVariable)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-6)).value;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-5)).value;
		NodoBase ss = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 5b");
                RESULT = new NodoFuncion(ft, ss, id);
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("func",4, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // func ::= func_type ID LPAREN func_args RPAREN BEGIN stmt_seq END 
            {
              NodoBase RESULT =null;
		tipoVariable ft = (tipoVariable)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-7)).value;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-6)).value;
		NodoBase fa = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-4)).value;
		NodoBase ss = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		
                if(modoDepuracion)
                    System.out.println("\t regla 5a");
                RESULT = new NodoFuncion(ft, ss, fa, id);
            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("func",4, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // funcs_seq ::= func 
            {
              NodoBase RESULT =null;
		NodoBase ft = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 4b");
                    RESULT = ft;
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("funcs_seq",3, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // funcs_seq ::= funcs_seq func 
            {
              NodoBase RESULT =null;
		NodoBase fs = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		NodoBase ft = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 4a");
                    NodoBase nodoActual = fs;
                    if(nodoActual != null)
                    {
                        while(nodoActual.TieneHermano())
                        {
                            nodoActual = nodoActual.getHermanoDerecha();
                        }
                        nodoActual.setHermanoDerecha(ft);
                        RESULT = fs;
                    }
                    else
                    {
                        RESULT = ft;
                    }
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("funcs_seq",3, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // main_program ::= BEGIN END 
            {
              NodoBase RESULT =null;
		
                        if(modoDepuracion)
                            System.out.println("\t regla 3b");
                        RESULT = new NodoFuncion();
                    
              CUP$parser$result = parser.getSymbolFactory().newSymbol("main_program",2, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // main_program ::= BEGIN stmt_seq END 
            {
              NodoBase RESULT =null;
		NodoBase ss = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		
                        if(modoDepuracion)
                            System.out.println("\t regla 3a");
                        RESULT = new NodoFuncion(ss);
                    
              CUP$parser$result = parser.getSymbolFactory().newSymbol("main_program",2, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // program_struct ::= main_program 
            {
              NodoBase RESULT =null;
		NodoBase mp = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                        if(modoDepuracion)
                            System.out.println("\t regla 2b");
                        RESULT = mp;
                    
              CUP$parser$result = parser.getSymbolFactory().newSymbol("program_struct",1, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // program_struct ::= funcs_seq main_program 
            {
              NodoBase RESULT =null;
		NodoBase fs = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		NodoBase mp = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                        if(modoDepuracion)
                            System.out.println("\t regla 2a");
                        mp.setHermanoDerecha(fs);
                        RESULT = mp;
                    
              CUP$parser$result = parser.getSymbolFactory().newSymbol("program_struct",1, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= program EOF 
            {
              Object RESULT =null;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		RESULT = start_val;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("$START",0, RESULT);
            }
          /* ACCEPT */
          CUP$parser$parser.done_parsing();
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // program ::= program_struct 
            {
              Object RESULT =null;
		NodoBase ps = (NodoBase)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
                    if(modoDepuracion)
                        System.out.println("\t regla 1"); 
                    ast = ps; 
                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("program",0, RESULT);
            }
          return CUP$parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

