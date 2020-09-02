  /*
    2    * Copyright (c) 1995, 2000, Oracle and/or its affiliates. All rights reserved.
    3    * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
    4    *
    5    * This code is free software; you can redistribute it and/or modify it
    6    * under the terms of the GNU General Public License version 2 only, as
    7    * published by the Free Software Foundation.  Oracle designates this
    8    * particular file as subject to the "Classpath" exception as provided
    9    * by Oracle in the LICENSE file that accompanied this code.
   10    *
   11    * This code is distributed in the hope that it will be useful, but WITHOUT
   12    * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
   13    * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
   14    * version 2 for more details (a copy is included in the LICENSE file that
   15    * accompanied this code).
   16    *
   17    * You should have received a copy of the GNU General Public License version
   18    * 2 along with this work; if not, write to the Free Software Foundation,
   19    * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
   20    *
   21    * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
   22    * or visit www.oracle.com if you need additional information or have any
   23    * questions.
   24    */
package vip.hengnai.wine.util.aes;

 import java.io.OutputStream;
import java.io.PushbackInputStream;

  /**
    32    * This class implements a BASE64 Character decoder as specified in RFC1521.
    33    *
    34    * This RFC is part of the MIME specification which is published by the
    35    * Internet Engineering Task Force (IETF). Unlike some other encoding
    36    * schemes there is nothing in this encoding that tells the decoder
    37    * where a buffer starts or stops, so to use it you will need to isolate
    38    * your encoded data into a single chunk and then feed them this decoder.
    39    * The simplest way to do that is to read all of the encoded data into a
    40    * string and then use:
    41    * <pre>
    42    *      byte    mydata[];
    43    *      Base64DecoderBase base64 = new Base64DecoderBase();
    44    *
    45    *      mydata = base64.decodeBuffer(bufferString);
    46    * </pre>
    47    * This will decode the String in <i>bufferString</i> and give you an array
    48    * of bytes in the array <i>myData</i>.
    49    *
    50    * On errors, this class throws a CeFormatException with the following detail
    51    * strings:
    52    * <pre>
    53    *    "Base64DecoderBase: Not enough bytes for an atom."
    54    * </pre>
    55    *
    56    * @author      Chuck McManis
    57    * @see         BaseCharacterEncoder
    58    * @see         Base64DecoderBase
    59    */

   public class Base64DecoderBase extends BaseCharacterDecoder {

 /** This class has 4 bytes per atom */
  @Override
  protected int bytesPerAtom() {
       return (4);
     }

     /** Any multiple of 4 will do, 72 might be common */
   @Override
   protected int bytesPerLine() {
       return (72);
     }

      /**
       * 74        * This character array provides the character to value map
       * 75        * based on RFC1521.
       * 76
       */
      private final static char[] PEM_ARRAY = {
              //       0   1   2   3   4   5   6   7
              'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
              'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
              'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
              'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
              'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
              'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
              'w', 'x', 'y', 'z', '0', '1', '2', '3',
              '4', '5', '6', '7', '8', '9', '+', '/'
      };

      private final static byte[] PEM_CONVERT_ARRAY = new byte[256];

     static {
         int value255 =255;
      for (int i = 0; i <value255  ; i++) {
           PEM_CONVERT_ARRAY[i] = -1;
       }
        for (int i = 0; i < PEM_ARRAY.length; i++) {
           PEM_CONVERT_ARRAY[PEM_ARRAY[i]] = (byte) i;
      }
      }

      byte[] decodeBuffer = new byte[4];
    /**
   103        * Decode one BASE64 atom into 1, 2, or 3 bytes of data.
   104        */
   @Override
   protected void decodeAtom(PushbackInputStream inStream, OutputStream outStream, int rem)
      throws java.io.IOException
    {
         int     i;
       byte    a = -1, b = -1, c = -1, d = -1;
        int value2=2;
      if (rem < value2) {
            throw new CeFormatException("Base64DecoderBase: Not enough bytes for an atom.");
        }
         do {
           i = inStream.read();
            if (i == -1) {
               throw new CeStreamException();
               }
         } while (i == '\n' || i == '\r');
         decodeBuffer[0] = (byte) i;

      i = readFully(inStream, decodeBuffer, 1, rem-1);
      if (i == -1) {
        throw new CeStreamException();
       }
        int value3=3;
      char value='=';
         if (rem > value3 && decodeBuffer[value3] == value) {
             rem = 3;
          }
         if (rem > value2 && decodeBuffer[value2] == value) {
             rem = 2;
        }
       switch (rem) {
        case 4:
           d = PEM_CONVERT_ARRAY[decodeBuffer[3] & 0xff];
            c = PEM_CONVERT_ARRAY[decodeBuffer[2] & 0xff];
            b = PEM_CONVERT_ARRAY[decodeBuffer[1] & 0xff];
            a = PEM_CONVERT_ARRAY[decodeBuffer[0] & 0xff];
         break;
       case 3:
            c = PEM_CONVERT_ARRAY[decodeBuffer[2] & 0xff];
           b = PEM_CONVERT_ARRAY[decodeBuffer[1] & 0xff];
           a = PEM_CONVERT_ARRAY[decodeBuffer[0] & 0xff];
           break;
     case 2:
         b = PEM_CONVERT_ARRAY[decodeBuffer[1] & 0xff];
        a = PEM_CONVERT_ARRAY[decodeBuffer[0] & 0xff];
             break;
             default:
                 break;
        }

        switch (rem) {
         case 2:
           outStream.write( (byte)(((a << 2) & 0xfc) | ((b >>> 4) & 3)) );
           break;
       case 3:
              outStream.write( (byte) (((a << 2) & 0xfc) | ((b >>> 4) & 3)) );
             outStream.write( (byte) (((b << 4) & 0xf0) | ((c >>> 2) & 0xf)) );
             break;
         case 4:
            outStream.write( (byte) (((a << 2) & 0xfc) | ((b >>> 4) & 3)) );
             outStream.write( (byte) (((b << 4) & 0xf0) | ((c >>> 2) & 0xf)) );
          outStream.write( (byte) (((c << 6) & 0xc0) | (d  & 0x3f)) );
        break;
            default:
                break;
  }
       return;
    }
  }