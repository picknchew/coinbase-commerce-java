package me.picknchew.coinbase.commerce.util;

import com.google.common.collect.ImmutableList;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.List;

public class StubProxySelector extends ProxySelector {
    private final List<Proxy> proxies = ImmutableList.of(Proxy.NO_PROXY);

    @Override
    public List<Proxy> select(URI uri) {
        return proxies;
    }

    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {}
}
