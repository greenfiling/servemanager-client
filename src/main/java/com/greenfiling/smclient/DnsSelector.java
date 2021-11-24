/**
 * Copyright 2021 Green Filing, LLC
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.greenfiling.smclient;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom {@link okhttp3.Dns} for {@link ApiHandle}.
 * <P>
 * This class exists solely to allow users of servemanager-client to specify IP version preferences. See {@link ApiHandle.Builder#ipMode(IpMode)} and
 * {@link IpMode}.
 * 
 * @author jetmore
 * @since 1.0.1
 */
public class DnsSelector implements okhttp3.Dns {
  /**
   * The IpMode enum is used to specify IP version preferences when connecting to remote websites
   * <P>
   * <UL>
   * <LI><B>SYSTEM</B> - the default order, defined by local system resolver</LI>
   * <LI><B>IPV4_ONLY</B> - only attempt connections to IPv4 addresses</LI>
   * <LI><B>IPV6_ONLY</B> - only attempt connections to IPv6 addresses</LI>
   * <LI><B>IPV4_FIRST</B> - attempt connections to both v4 and v6 addresses, but try IPv4 addresses first</LI>
   * <LI><B>IPV6_FIRST</B> - attempt connections to both v4 and v6 addresses, but try IPv6 addresses first</LI>
   * </UL>
   */
  public enum IpMode {
    SYSTEM, IPV6_FIRST, IPV4_FIRST, IPV6_ONLY, IPV4_ONLY
  };

  private static final Logger logger = LoggerFactory.getLogger(DnsSelector.class);
  private IpMode ipMode;

  public DnsSelector() {
    this(IpMode.SYSTEM);
  }

  public DnsSelector(IpMode mode) {
    ipMode = mode;
  }

  @Override
  public List<InetAddress> lookup(String arg0) throws UnknownHostException {
    List<InetAddress> addresses = SYSTEM.lookup(arg0);
    List<InetAddress> ipv4Addresses = new ArrayList<InetAddress>();
    List<InetAddress> ipv6Addresses = new ArrayList<InetAddress>();

    for (InetAddress addr : addresses) {
      logger.trace("lookup - before applying IpMode {}", addr.getAddress());

      if (addr instanceof Inet4Address) {
        ipv4Addresses.add(addr);
      } else if (addr instanceof Inet6Address) {
        ipv6Addresses.add(addr);
      }
    }

    List<InetAddress> retAddresses = new ArrayList<InetAddress>();
    if (ipMode == IpMode.SYSTEM) {
      retAddresses = addresses;
    } else if (ipMode == IpMode.IPV6_FIRST) {
      retAddresses.addAll(ipv6Addresses);
      retAddresses.addAll(ipv4Addresses);
    } else if (ipMode == IpMode.IPV4_FIRST) {
      retAddresses.addAll(ipv4Addresses);
      retAddresses.addAll(ipv6Addresses);
    } else if (ipMode == IpMode.IPV6_ONLY) {
      retAddresses.addAll(ipv6Addresses);
    } else if (ipMode == IpMode.IPV4_ONLY) {
      retAddresses.addAll(ipv4Addresses);
    }

    for (InetAddress addr : retAddresses) {
      logger.trace("lookup - after applying IpMode {}", addr.getAddress());
    }

    return retAddresses;
  }

}
