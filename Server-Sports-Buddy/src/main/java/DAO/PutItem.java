public class PutItem {

    public static void main(String[] args) {
        putItemToDynamoDB()
    }

    public static void putItemToDynamoDB() {

        /* Create DynamoDB Client Object */
        AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder
            .standard()
            .withRegion(Regions.AP_SOUTH_1)
            .withCredentials(new AWSStaticCredentialsProvider(
                new BasicAWSCredentials("ACCESS_KEY","SECRET_KEY")))
            .build();

        /* Create an Object of PutItemRequest */
        PutItemRequest request = new PutItemRequest();

        /* Setting Table Name */
        request.setTableName("JOB");

        /* Setting Consumed Capacity */
        request.setReturnConsumedCapacity(ReturnConsumedCapacity.TOTAL);

        /* To get old value of item's attributes before it is overwritten */
        request.setReturnValues(ReturnValue.ALL_OLD);

        /* Create a Map of attributes */
        Map<String, AttributeValue> map = new HashMap<>();
        map.put("CountryId", new AttributeValue("18"));
        map.put("JobId", (new AttributeValue()).withN("1"));

        map.put("CompanyName", new AttributeValue("Amazon"));
        map.put("JobTitle", new AttributeValue("Software Engineer"));

        request.setItem(map);

        try {
            /* Send Put Item Request */
            PutItemResult result = dynamoDB.putItem(request);

            System.out.println("Status : " + result.getSdkHttpMetadata().getHttpStatusCode());

            System.out.println("Consumed Capacity : " + result.getConsumedCapacity().getCapacityUnits());

            /* Printing Old Attributes Name and Values */
            if(result.getAttributes() != null) {
                result.getAttributes().entrySet().stream()
                    .forEach( e -> System.out.println(e.getKey() + " " + e.getValue()));
            }

        } catch (AmazonServiceException e) {

            System.out.println(e.getErrorMessage());

        }

    }

}