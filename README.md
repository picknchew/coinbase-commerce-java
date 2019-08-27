# coinbase-commerce-java

This is a Java wrapper based on the documentation from Coinbase Commerce.

## Usage
Create a new Coinbase instance using CoinbaseBuilder and your API key.
The webhook secret is optional. It is only required if you are using 
webhooks.
```
Coinbase coinbase = new CoinbaseBuilder.withApiKey("API_KEY")
        .withWebhookSecret("SECRET").build();
```

## License
```
 Copyright 2018 Franco Chen
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
     http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.