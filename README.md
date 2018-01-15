# Use GPS with IndoorLocation


This providers allows you to use the device GPS as IndoorLocation. The accuracy of GPS inside buildings is usually pretty bad, and it does not provide any floor.

The main use of this provider is to provide a position on the map when the user is outside and no other indoor positioning system is in sight. To switch between GPS and other providers, the [selector](https://github.com/IndoorLocation/selector-indoor-location-provider-android) can be used.

## Use

Instanciate the provider:

```
gpsIndoorLocationProvider = new GPSIndoorLocationProvider(this);
```

Set the provider in your Mapwize SDK:

```
mapwizePlugin.setLocationProvider(gpsIndoorLocationProvider);     
```

## Demo app

A simple demo application to test the provider is available in the /demoapp directory.

You will need to set your credentials in GPSIndoorLocationProviderDemoApplication and MapActivity.

Sample keys are given for Mapwize and Mapbox. Please note that those keys can only be used for testing purposes, with very limited traffic, and cannot be used in production. Get your own keys from [mapwize.io](https://www.mapwize.io) and [mapbox.com](https://www.mapbox.com). Free accounts are available.

## Contribute

Contributions are welcome. We will be happy to review your PR.

## Support

For any support with this provider, please do not hesitate to contact [support@mapwize.io](mailto:support@mapwize.io)

## License

MIT