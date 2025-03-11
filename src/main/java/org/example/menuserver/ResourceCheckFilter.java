package org.example.menuserver;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.Filter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

@Component
public class ResourceCheckFilter implements Filter {

    private final OperatingSystemMXBean osBean;

    public ResourceCheckFilter() {
        this.osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (!resourcesAvailable()) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "Not enough resources");
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean resourcesAvailable() {
        long freePhysicalMemory = osBean.getFreeMemorySize();
        double cpuLoad = osBean.getCpuLoad();

        System.out.println("Free physical memory: " + freePhysicalMemory);
        System.out.println("System CPU load: " + cpuLoad);
        boolean memoryOk = freePhysicalMemory > 100L * 1024 * 1024; // 100 МБ
        boolean cpuOk = cpuLoad >= 0.0 && cpuLoad < 0.8;
        return memoryOk && cpuOk;
    }
}
