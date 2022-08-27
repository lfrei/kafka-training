SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

use events;

CREATE TABLE `events` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `XML_EVENT` mediumtext DEFAULT NULL,
  `CREATETIME` timestamp,
  `PERSON_IDENTIFIER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `events`
--

INSERT INTO `events` (`ID`, `XML_EVENT`, `CREATETIME`, `PERSON_IDENTIFIER`) VALUES
(1, '\"<eCH-0020:delivery xmlns:xsi=\"\"http://www.w3.org/2001/XMLSchema-instance\"\" xmlns:xsd=\"\"http://www.w3.org/2001/XMLSchema\"\" version=\"\"3.0\"\" xmlns:eCH-0020=\"\"http://www.ech.ch/xmlns/eCH-0020/3\"\">\r\n  <eCH-0020:deliveryHeader xmlns:eCH-0058=\"\"http://www.ech.ch/xmlns/eCH-0058/5\"\">\r\n  </eCH-0020:deliveryHeader>\r\n  <eCH-0020:moveIn>\r\n    <eCH-0020:moveInPerson>\r\n      <eCH-0020:personIdentification xmlns:eCH-0044=\"\"http://www.ech.ch/xmlns/eCH-0044/4\"\">\r\n        <eCH-0044:officialName>Musterfrau</eCH-0044:officialName>\r\n        <eCH-0044:firstName>Lara</eCH-0044:firstName>\r\n        <eCH-0044:sex>2</eCH-0044:sex>\r\n        <eCH-0044:dateOfBirth>\r\n          <eCH-0044:yearMonthDay>2000-04-03</eCH-0044:yearMonthDay>\r\n        </eCH-0044:dateOfBirth>\r\n      </eCH-0020:personIdentification>\r\n      <eCH-0020:birthInfo>\r\n        <eCH-0020:birthData xmlns:eCH-0011=\"\"http://www.ech.ch/xmlns/eCH-0011/8\"\">\r\n          <eCH-0011:dateOfBirth xmlns:eCH-0044=\"\"http://www.ech.ch/xmlns/eCH-0044/4\"\">\r\n            <eCH-0044:yearMonthDay>2000-04-03</eCH-0044:yearMonthDay>\r\n          </eCH-0011:dateOfBirth>\r\n    </eCH-0020:moveInPerson>\r\n  </eCH-0020:moveIn>\r\n</eCH-0020:delivery>\"', '2022-08-02', 'LM'),
(2, '\"<eCH-0020:delivery xmlns:xsi=\"\"http://www.w3.org/2001/XMLSchema-instance\"\" xmlns:xsd=\"\"http://www.w3.org/2001/XMLSchema\"\" version=\"\"3.0\"\" xmlns:eCH-0020=\"\"http://www.ech.ch/xmlns/eCH-0020/3\"\">\r\n  <eCH-0020:deliveryHeader xmlns:eCH-0058=\"\"http://www.ech.ch/xmlns/eCH-0058/5\"\">\r\n  </eCH-0020:deliveryHeader>\r\n  <eCH-0020:moveOut>\r\n    <eCH-0020:moveOutPerson>\r\n      <eCH-0020:personIdentification xmlns:eCH-0044=\"\"http://www.ech.ch/xmlns/eCH-0044/4\"\">\r\n        <eCH-0044:officialName>Mustermann</eCH-0044:officialName>\r\n        <eCH-0044:firstName>Urs</eCH-0044:firstName>\r\n        <eCH-0044:sex>1</eCH-0044:sex>\r\n        <eCH-0044:dateOfBirth>\r\n          <eCH-0044:yearMonthDay>1988-02-01</eCH-0044:yearMonthDay>\r\n        </eCH-0044:dateOfBirth>\r\n      </eCH-0020:personIdentification>\r\n      <eCH-0020:birthInfo>\r\n        <eCH-0020:birthData xmlns:eCH-0011=\"\"http://www.ech.ch/xmlns/eCH-0011/8\"\">\r\n          <eCH-0011:dateOfBirth xmlns:eCH-0044=\"\"http://www.ech.ch/xmlns/eCH-0044/4\"\">\r\n            <eCH-0044:yearMonthDay>2000-04-03</eCH-0044:yearMonthDay>\r\n          </eCH-0011:dateOfBirth>\r\n    </eCH-0020:moveOutPerson>\r\n  </eCH-0020:moveOut>\r\n</eCH-0020:delivery>\"', '2022-08-01', 'UM'),
(3, '\"<eCH-0020:delivery xmlns:xsi=\"\"http://www.w3.org/2001/XMLSchema-instance\"\" xmlns:xsd=\"\"http://www.w3.org/2001/XMLSchema\"\" version=\"\"3.0\"\" xmlns:eCH-0020=\"\"http://www.ech.ch/xmlns/eCH-0020/3\"\">\r\n  <eCH-0020:deliveryHeader xmlns:eCH-0058=\"\"http://www.ech.ch/xmlns/eCH-0058/5\"\">\r\n  </eCH-0020:deliveryHeader>\r\n  <eCH-0020:move>\r\n    <eCH-0020:movePerson>\r\n      <eCH-0020:personIdentification xmlns:eCH-0044=\"\"http://www.ech.ch/xmlns/eCH-0044/4\"\">\r\n        <eCH-0044:officialName>Schlueter</eCH-0044:officialName>\r\n        <eCH-0044:firstName>Jürg</eCH-0044:firstName>\r\n        <eCH-0044:sex>1</eCH-0044:sex>\r\n        <eCH-0044:dateOfBirth>\r\n          <eCH-0044:yearMonthDay>1972-02-01</eCH-0044:yearMonthDay>\r\n        </eCH-0044:dateOfBirth>\r\n      </eCH-0020:personIdentification>\r\n      <eCH-0020:birthInfo>\r\n        <eCH-0020:birthData xmlns:eCH-0011=\"\"http://www.ech.ch/xmlns/eCH-0011/8\"\">\r\n          <eCH-0011:dateOfBirth xmlns:eCH-0044=\"\"http://www.ech.ch/xmlns/eCH-0044/4\"\">\r\n            <eCH-0044:yearMonthDay>2000-04-03</eCH-0044:yearMonthDay>\r\n          </eCH-0011:dateOfBirth>\r\n    </eCH-0020:movePerson>\r\n  </eCH-0020:move>\r\n</eCH-0020:delivery>\"', '2022-08-02', 'JS');
GRANT ALL PRIVILEGES ON events. * TO 'kafka_training';
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;




