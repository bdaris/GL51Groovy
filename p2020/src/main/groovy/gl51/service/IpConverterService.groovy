package gl51.service

import gl51.data.Ip

/**
 * ceci convertit une Ip en string
 */
interface  IpConverterService {

    /**
     * convertit une IP en string
     * @return
     */
    String getAndConvertIp()
}