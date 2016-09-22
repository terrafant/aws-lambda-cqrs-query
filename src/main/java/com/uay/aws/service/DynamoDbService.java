package com.uay.aws.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.uay.aws.domain.AggregatedItem;

import java.util.List;
import java.util.stream.Collectors;

public class DynamoDbService {

    private static final String AGGREGATED_CQRS = "aggregated-cqrs";
    public static final AmazonDynamoDBClient CLIENT = new AmazonDynamoDBClient()
            .withRegion(Regions.EU_CENTRAL_1);
//                .withEndpoint("http://localhost:8000");
    public static final String KEY_ID = "id";
    public static final String KEY_TEXT = "text";
    public static final String KEY_COUNT = "count";
    private DynamoDB dynamoDB = new DynamoDB(CLIENT);
    private Table table = dynamoDB.getTable(AGGREGATED_CQRS);

    public List<AggregatedItem> retrieveItem() {
        ItemCollection<ScanOutcome> scan = table.scan(new ScanSpec());
        ScanResult scanResult = CLIENT.scan(new ScanRequest().withTableName(AGGREGATED_CQRS));
        return scanResult.getItems().stream().map(item -> new AggregatedItem(
                item.get(KEY_TEXT).getS(),
                item.get(KEY_COUNT).getN()
        )).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(new DynamoDbService().retrieveItem());
    }

}
