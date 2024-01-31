package com.ecommerce.backend.service;

import com.ecommerce.backend.model.CustomerModel;
import com.ecommerce.backend.model.ItemModel;
import com.ecommerce.backend.model.OrderModel;
import com.ecommerce.backend.repository.OrderRepository;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private HttpServletResponse httpServletResponse;

    //CREATE
    public OrderModel createOrder(OrderModel orderModel){
        Long customerId = orderModel.getCustomerId();
        Long itemId = orderModel.getItemId();

        Optional<CustomerModel> customer = customerService.getCustomerById(customerId);
        Optional<ItemModel> item = itemService.getItemById(itemId);

        orderModel.setOrderDate(Date.valueOf(LocalDate.now()));
        if (customer.isPresent() && item.isPresent()){
            BigDecimal totalPrice = item.get().getPrice().multiply(BigDecimal.valueOf(orderModel.getQuantity()));

            orderModel.setCustomerModel(customer.get());
            orderModel.setItemModel(item.get());
            orderModel.setTotalPrice(totalPrice);
            orderModel.setOrderDate(Date.valueOf(LocalDate.now()));
            return orderRepository.save(orderModel);
        } else {
            logger.warn("Customer Id & Item Id is not found!");
            return null;
        }
    }

    //READ
    public List<OrderModel> getAllOrders(){
        return orderRepository.findAll();
    }

    public Optional<OrderModel> getOrderById(Long orderId){
        return orderRepository.findById(orderId);
    }

    //UPDATE
    public OrderModel updateOrder(Long orderId, OrderModel updateOrder){
        Optional<OrderModel> existingOrderOptional = orderRepository.findById(orderId);

        if (existingOrderOptional.isPresent()){
            OrderModel existingOrder = existingOrderOptional.get();
            existingOrder.setQuantity(updateOrder.getQuantity());
            BigDecimal totalPrice = existingOrder.getItemModel().getPrice().multiply(BigDecimal.valueOf(updateOrder.getQuantity()));
            existingOrder.setTotalPrice(totalPrice);

            logger.info("Order updated: {}", updateOrder);
            return orderRepository.save(existingOrder);
        } else {
            logger.warn("Order not found with ID: {}", orderId);
            return null;
        }
    }

    //DELETE
    public void deleteOrder(Long orderId){
        orderRepository.deleteById(orderId);
    }

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException{
        String path = "C:\\Users\\andix\\OneDrive\\Dokumen\\jasperReport";
        List<OrderModel> orders = orderRepository.findAll();

        File file = ResourceUtils.getFile("classpath:TestReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orders);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Averroes");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\ReportOrder.pdf");
        }

        return "report generated in path : " + path;
    }
}
