/* ==================================================================
 * PacketEnvelope.java - 14/05/2018 9:13:48 AM
 * 
 * Copyright 2018 SolarNetwork.net Dev Team
 * 
 * This program is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as 
 * published by the Free Software Foundation; either version 2 of 
 * the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License 
 * along with this program; if not, write to the Free Software 
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 
 * 02111-1307 USA
 * ==================================================================
 */

package net.solarnetwork.node.hw.yaskawa.ecb;

/**
 * Enumeration of the ECB packet envelope encoding.
 * 
 * @author matt
 * @version 1.0
 */
public enum PacketEnvelope {

	Start((byte) 0x02),

	End((byte) 0x03);

	private final byte code;

	PacketEnvelope(byte code) {
		this.code = code;
	}

	/**
	 * Get the code for this enum.
	 * 
	 * @return the code value
	 */
	public byte getCode() {
		return code;
	}

	/**
	 * Get an enum value from a code.
	 * 
	 * @param code
	 *        the code to get the enum for
	 * @return the enum
	 * @throws IllegalArgumentException
	 *         if {@code code} is not supported
	 */
	public static PacketEnvelope forCode(byte code) {
		for ( PacketEnvelope e : PacketEnvelope.values() ) {
			if ( e.code == code ) {
				return e;
			}
		}
		throw new IllegalArgumentException("Code [" + code + "] is not supported");
	}

}
