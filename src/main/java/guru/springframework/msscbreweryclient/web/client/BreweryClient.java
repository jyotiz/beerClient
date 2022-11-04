package guru.springframework.msscbreweryclient.web.client;


import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    private String apiHost;
    private final RestTemplate restTemplate;

    public final String beerPathV1 = "/api/v1/beer/";
    public final String custPathV1 = "/api/v1/customer/";

    //Inject springBoots restTemplateBuilder
    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public BeerDto getBeerByID(UUID uuid) {
        return restTemplate.getForObject(apiHost + beerPathV1 + uuid.toString(), BeerDto.class);
    }

    public URI saveNewBeer(BeerDto beerDto) {
        return restTemplate.postForLocation(apiHost + beerPathV1, beerDto);
    }

    public void updateBeer(UUID uuid, BeerDto beerDto) {
        restTemplate.put(apiHost + beerPathV1 + uuid.toString(), beerDto);
    }

    public void deleteBeer(UUID uuid) {
        restTemplate.delete(apiHost + beerPathV1.toString() + uuid);
    }

    public CustomerDto getCustomerById(UUID uuid) {
        return restTemplate.getForObject(apiHost + custPathV1 + uuid.toString(), CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto customerDto){
        return  restTemplate.postForLocation(apiHost + custPathV1, customerDto);
    }

    public void updateCustomer(UUID uuid, CustomerDto customerDto){
         restTemplate.put(apiHost + custPathV1 + uuid.toString(), customerDto);
    }
    public void deleteCustomer(UUID uuid) {
        restTemplate.delete(apiHost + custPathV1.toString() + uuid);
    }

}


