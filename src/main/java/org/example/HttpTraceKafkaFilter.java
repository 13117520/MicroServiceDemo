package org.example;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class HttpTraceKafkaFilter extends OncePerRequestFilter {

    @Autowired
    private HttpTraceProducer httpTraceProducer;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpTraceData httpTraceData = new HttpTraceData();
        httpTraceData.setMethod(request.getMethod());
        httpTraceData.setUrl(request.getRequestURL().toString());
        httpTraceData.setStatus(response.getStatus());
        // Set other fields based on your requirements

        // Send the trace data to Kafka
        httpTraceProducer.sendHttpTrace(httpTraceData);

        filterChain.doFilter(request, response);
    }
}
