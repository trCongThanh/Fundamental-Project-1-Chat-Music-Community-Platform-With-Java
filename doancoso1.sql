-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.32-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table doancoso1.baihat
CREATE TABLE IF NOT EXISTS `baihat` (
  `IDbaihat` varchar(50) NOT NULL,
  `Tenbaihat` varchar(50) NOT NULL,
  `Theloai` varchar(50) NOT NULL,
  `Luotthich` int(11) NOT NULL DEFAULT 0,
  `songUrl` varchar(200) DEFAULT NULL,
  `imgUrl` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`IDbaihat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table doancoso1.baihat: ~19 rows (approximately)
INSERT INTO `baihat` (`IDbaihat`, `Tenbaihat`, `Theloai`, `Luotthich`, `songUrl`, `imgUrl`) VALUES
	('1', 'Chúng ta của tương lai', 'VPOP - R&B', 0, 'https://drive.google.com/uc?id=1-341IN4kWu_T82OzUcDdq9k2gvh90HrZ', 'https://drive.google.com/uc?id=1h3a8rW3NbbR2ZjIufjDwO-mE5ApEEPjn'),
	('10', 'Cầu Vòng Khuyết', 'Bolero - Ballad', 0, 'https://drive.google.com/uc?id=1hIKjDJVJHtyXJ5Owlxe019Usrm9uOXYK', 'https://drive.google.com/uc?id=13PaEf7go1Kqi5j764CuGbdtrkcPTyKyb'),
	('11', 'Đưa Nhau Đi Trốn', 'RAP - HIPHOP', 0, 'https://drive.google.com/uc?id=1qbgQ0h_B24RrGP_JaVKqUh4aC_zr0VUx', 'https://drive.google.com/uc?id=1O68F-u2eKKOFcYC80yiSMacJsgMm4TpM'),
	('12', 'Một Triệu Like', 'RAP', 0, 'https://drive.google.com/uc?id=1le1lxrTsLrdxjrvAiLx1xYFIbV3SFaYZ', 'https://drive.google.com/uc?id=1efdrZZ-mXC316_3ixN0umPOtvIYAF596'),
	('13', 'Mười Năm (Lộn xộn 3)', 'RAP - Ballad', 0, 'https://drive.google.com/uc?id=1NXZ3HWAI4vfLi5U8dqNVMP1JxBjQaE10', 'https://drive.google.com/uc?id=1270jJTasdvDQ_192ZLpUoPPng-Kj_OLS'),
	('14', 'Forever In Love', 'JAZZ', 0, 'https://drive.google.com/uc?id=1MlNy6NprNOtDSd3zHLIT-ZTma4-aZJ4b', 'https://drive.google.com/uc?id=1NxkUaTu_KaeDgP-azQ-ZL7K24IV_ybKj'),
	('15', 'I Do', 'US-UK', 0, 'https://drive.google.com/uc?id=1Bldb67FIIZbUNNr5ZLXVBdXVfy6sHDjD', 'https://drive.google.com/uc?id=1-4fn6rsVZ2zvRj7WxyEwBzJnh6QdPmN-'),
	('16', 'Từ Nơi Tôi Sinh Ra', 'HIPHOP', 0, 'https://drive.google.com/uc?id=1dtEYowF1sMMjLuUVtACo3hJG3HfqD_RS', 'https://drive.google.com/uc?id=1MpOCG0wkq9JcKlXA7PWXDa0_Xsp-DloJ'),
	('17', 'Comethru', 'US-UK', 0, 'https://drive.google.com/uc?id=1viUxPj8QBxOHuuOivQBHcHk8NNKBVn7a', 'https://drive.google.com/uc?id=1zgICCXzVSwph11OFnNQG7dNwBYcztq0R'),
	('18', 'The Moment', 'JAZZ', 0, 'https://drive.google.com/uc?id=1htx6qFowM_qD99B5ywrPJnaYc7ndUTsF', 'https://drive.google.com/uc?id=1bWabimMOY_XYG5NJBc5gPCxNzLiBTRQv'),
	('19', 'Lâu Đài Tình Ái', 'Bolero', 0, 'https://drive.google.com/uc?id=1N_Afz7dLalLrt6IlGc2bspR1vQM7SO_Q', 'https://drive.google.com/uc?id=1GBlSxEcLhX_BKOINrkEywS--ZZ-_GcEN'),
	('2', 'Cupid', 'KPOP', 0, 'https://drive.google.com/uc?id=1N57HNfUVQ3Tx2YWiCqF6ni1HbaKivsof', 'https://drive.google.com/uc?id=1Dny9FFoUtbWMgYcto_R1zEE6R_EHiATG'),
	('20', 'Lần Cuối', 'VPOP', 0, 'https://drive.google.com/uc?id=1GoC1bOMh28gDmPGIBgOKice3o1N-fiR6', 'https://drive.google.com/uc?id=1XVHOpM1z2h1CskUzAOtiqtFEmoLa5gcw'),
	('21', 'Pretender', 'JPOP', 0, 'https://drive.google.com/uc?id=11oRdGo6X_zd5ssFrVD9SvbZnyU4-xHcu', 'https://drive.google.com/uc?id=1jn-LIiNMgIMc95qOPVIQNLXbbPcuMk4X'),
	('3', 'Đi về nhà', 'HIPHOP - RAP', 0, 'https://drive.google.com/uc?id=1dZnmxKylkSfv0A504msh8kNbGEzB3MHf', 'https://drive.google.com/uc?id=1r6do9jG_uBRhsls-V8MQg3KgiD2nisOh'),
	('4', 'Chúng ta của hiện tại', 'VPOP - R&B', 0, 'https://drive.google.com/uc?id=1vKL2ZWBon5JXa4p3mAWXEotgtkJ8CXGc', 'https://drive.google.com/uc?id=1_eLMZhuc6qqSrYcNteOPC2U9TvL98ble'),
	('5', 'Lemon Tree', 'US-UK', 0, 'https://drive.google.com/uc?id=16vZq3xddFTH6qTyjnQVoWxw-agCnONx2', 'https://drive.google.com/uc?id=1dbWWmcM6YR5sTZnvz84AiSsMTQh26dla'),
	('6', 'The Nights', 'R&B', 0, 'https://drive.google.com/uc?id=129jKLEbP72A8K0EO0xHGe2nU6HuHYai1', 'https://drive.google.com/uc?id=1yxGrriilpzcArz2NIaz4baVZpDPW7cxT'),
	('7', 'Trú Mưa', 'VPOP', 0, 'https://drive.google.com/uc?id=1xYlcNh2oSqvIX0WePAi0-o2JxMDcnHFF', 'https://drive.google.com/uc?id=1SknRYfhX3H6ApSU7nWIythba51NrNuOm'),
	('8', 'Haru Haru', 'KPOP - RAP - HIPHOP', 0, 'https://drive.google.com/uc?id=1RtpsZlMu75fiaNuNb6celmuvSOdW81VV', 'https://drive.google.com/uc?id=1zceyNeqSWweaIE5Y3x50Q8Mg9fsO8qmD'),
	('9', 'Kill This Love', 'KPOP - HIPHOP', 0, 'https://drive.google.com/uc?id=1BCxf2aZXnztpMBlDMEp1QHflq0spmPXw', 'https://drive.google.com/uc?id=1pufwLnidqRcwPH2DgX3KBUeQOavLIgKb');

-- Dumping structure for table doancoso1.bieudien
CREATE TABLE IF NOT EXISTS `bieudien` (
  `IDbaihat` varchar(50) NOT NULL,
  `IDcasi` varchar(50) NOT NULL,
  `IDbieudien` varchar(50) NOT NULL,
  PRIMARY KEY (`IDbieudien`),
  KEY `FK_bieudien_baihat` (`IDbaihat`),
  KEY `FK_bieudien_casi` (`IDcasi`),
  CONSTRAINT `FK_bieudien_baihat` FOREIGN KEY (`IDbaihat`) REFERENCES `baihat` (`IDbaihat`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_bieudien_casi` FOREIGN KEY (`IDcasi`) REFERENCES `casi` (`IDcasi`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table doancoso1.bieudien: ~24 rows (approximately)
INSERT INTO `bieudien` (`IDbaihat`, `IDcasi`, `IDbieudien`) VALUES
	('1', 'cs1', 'bd1'),
	('9', 'cs8', 'bd10'),
	('20', 'cs16', 'bd11'),
	('19', 'cs9', 'bd12'),
	('5', 'cs10', 'bd13'),
	('12', 'cs3', 'bd14'),
	('13', 'cs3', 'bd15'),
	('13', 'cs15', 'bd15a'),
	('21', 'cs18', 'bd16'),
	('18', 'cs14', 'bd17'),
	('6', 'cs6', 'bd18'),
	('7', 'cs11', 'bd19'),
	('2', 'cs2', 'bd2'),
	('16', 'cs12', 'bd20'),
	('11', 'cs3', 'bd21'),
	('11', 'cs15', 'bd21a'),
	('3', 'cs3', 'bd3'),
	('3', 'cs4', 'bd3a'),
	('4', 'cs1', 'bd4'),
	('10', 'cs17', 'bd5'),
	('17', 'cs13', 'bd6'),
	('14', 'cs14', 'bd7'),
	('8', 'cs7', 'bd8'),
	('15', 'cs5', 'bd9');

-- Dumping structure for table doancoso1.casi
CREATE TABLE IF NOT EXISTS `casi` (
  `IDcasi` varchar(50) NOT NULL,
  `Tencasi` varchar(50) NOT NULL,
  `Tieusu` varchar(500) NOT NULL,
  `imgAvatar` varchar(200) DEFAULT NULL,
  `imgBG` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`IDcasi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table doancoso1.casi: ~16 rows (approximately)
INSERT INTO `casi` (`IDcasi`, `Tencasi`, `Tieusu`, `imgAvatar`, `imgBG`) VALUES
	('cs1', 'Sơn Tùng M-TP', 'Nguyễn Thanh Tùng, thường được biết đến với nghệ danh Sơn Tùng M-TP, là một nam ca sĩ kiêm sáng tác nhạc, rapper và diễn viên người Việt Nam. Sinh ra ở thành phố Thái Bình, Sơn Tùng thường đi hát ở Cung văn hoá thiếu nhi tỉnh Thái Bình và học chơi đàn organ từ nhỏ', 'https://drive.google.com/uc?id=1wy4F0h6rbTVvhFap8WBs89W-5j_ZHIse', 'https://drive.google.com/uc?id=1i9JYBCM9_F_F9FpkN2dZp7d1B2pnxzdM'),
	('cs10', 'Fool Garden', 'Fools Garden là một ban nhạc Đức được thành lập vào năm 1991. Cùng năm đó, Fools Garden đã cho phát hành album trùng tên của nhóm. Các thành viên ban đầu của nhóm gồm có Peter Freudenthaler, Volker Hinkel, Thomas Mangold, Roland Röhl và Ralf Wochele. Năm 1993, album Once in a Blue Moon của ban nhạc đã đánh dấu lần đầu tiên có sự hiện diện của một bộ khung ban nhạc hoàn chỉnh.', 'https://drive.google.com/uc?id=13xA9kTdW2E850OD5PoIWikveT_thmjQk', NULL),
	('cs11', 'HKT', 'HKT là một nhóm nhạc pop người Việt Nam được Hải Âu Entertainment thành lập vào năm 2005 gồm 3 thành viên: Hồ Gia Hùng, Lý Tuấn Kiệt, Phạm Linh Phương (nghệ danh Ti Ti). Ba chữ cái đầu của những cái tên này ghép lại với nhau để cho ra cái tên chính thức của nhóm.', 'https://drive.google.com/uc?id=1ZXSWQxMqhevRff1NVYBNV1T5LKr5apoA', NULL),
	('cs12', 'Jack 97', 'Trịnh Trần Phương Tuấn (sinh ngày 12 tháng 4 năm 1997), thường được biết đến với nghệ danh Jack – J97, là một nam ca sĩ kiêm sáng tác nhạc, rapper, diễn viên người Việt Nam.', 'https://drive.google.com/uc?id=1cHFKKHAKVW5lM22wRPVBzXACyqCIctVn', NULL),
	('cs13', 'Jeremy Zucker', 'Xuất thân từ Franklin Lakes, New Jersey, Zucker lớn lên trong một gia đình âm nhạc với cha mẹ và hai anh trai. Khi còn là học sinh tại Trường trung học Ramapo, anh ấy bắt đầu sáng tác nhạc trong phòng ngủ của mình và sau đó tham gia một ban nhạc tên là "Foreshadows". Bài hát đầu tiên anh ấy từng viết thực ra là về chứng sợ độ cao của anh trai mình. Sau khi tốt nghiệp trung học, anh ấy theo học tại Cao đẳng Colorado, nơi anh ấy tốt nghiệp vào năm 2018 với bằng sinh học phân tử. Trước khi sản xuất', 'https://drive.google.com/uc?id=1DkvbbPxQH9SaHcEgkimmr8ORjfpY7t2J', NULL),
	('cs14', 'Kenny G', 'Kenneth Bruce Gorelick (sinh ngày 5 tháng 6 năm 1956), được biết nhiều với nghệ danh Kenny G, là một nhạc sĩ saxophone người Mỹ. Album thứ tư của ông, Duotones, đã đưa ông trở nên nổi tiếng toàn thế giới vào năm 1986. Nhạc cụ chính của Kenny là kèn soprano saxophone, nhưng đôi lúc cũng chơi kèn alto, tenor saxophone, flute. Kenny G là nhạc sĩ chơi nhạc viết cho nhạc khí thời hiện đại mà các đĩa nhạc bán chạy nhất, nằm trong danh sách nghệ sĩ âm nhạc bán đĩa nhạc chạy nhất của mọi thời, với tổng ', 'https://drive.google.com/uc?id=1F1fJ0TaVpGuz6yk-oVcoeEAhdnxbVJPa', NULL),
	('cs15', 'Linh Cáo', 'Nguyễn Thùy Linh (sinh ngày 17 tháng 7 năm 1990) tại thành phố Huế,Việt Nam là một ca sĩ , người viết lời nhạc được biết nhiều hơn với nghệ danh Linh Cáo một thành viên thuộc nhóm Anh Em rap.', 'https://drive.google.com/uc?id=1qw8SJ7Ozdxzguj7S9d90gYje-oKy0rIm', NULL),
	('cs16', 'Ngọt', 'Ngọt là ban nhạc pop rock Việt Nam gồm 4 thành viên Vũ Đinh Trọng Thắng (hát, guitar đệm), Phan Việt Hoàng (guitar bass), Nguyễn Hùng Nam Anh (trống) và Hoàng Chí Trung (keyboard). Kể từ khi được thành lập vào năm 2013 tại Hà Nội, Ngọt sớm tạo được tiếng vang trong cộng đồng âm nhạc underground trên toàn quốc và qua nhiều bản thu âm trên mạng xã hội, ban nhạc dần trở thành một trong những nghệ sĩ nổi bật trong dòng nhạc indie tại Việt Nam. Năm 2015, Ngọt cho ra mắt album đầu tay cùng tên tuyển t', 'https://drive.google.com/uc?id=1Tee-vLd4hRn6OtdFtqc586hjv77dvhXj', NULL),
	('cs17', 'Tuấn Hưng', 'Tuấn Hưng, tên thật là Nguyễn Tuấn Hưng, sinh ngày 5 tháng 10 năm 1978 tại Hà Nội, là một ca sĩ nhạc trẻ của Việt Nam. Anh được công chúng biết đến qua vai trò là thành viên của nhóm nhạc Quả dưa hấu - nhóm nhạc nổi tiếng của Hà Nội vào những năm cuối thế kỉ 20 và qua vai trò một ca sĩ solo với các ca khúc Tình yêu lung linh, Tình là gì, Dĩ vãng cuộc tình, Chia xa, Tình yêu nào phải trò chơi..., và gần đây nhất là Vol 10. Tìm lại bầu trời (2012)', 'https://drive.google.com/uc?id=10nfInKfqjcTNbwOUQWmu5ntd4L0ZOd05', NULL),
	('cs18', 'Official HIGE DANdism', 'Official HIGE DANdism là một ban nhạc pop rock Nhật Bản được thành lập vào năm 2011 gồm 4 thành viên: Masato (Guitar, Chủ lời), Satoshi (Guitar), Akihiro (Bass) và Makoto (Trống). Họ được biết đến với phong cách âm nhạc độc đáo, kết hợp các yếu tố pop, rock và R&B và nhanh chóng trở thành một trong những ban nhạc được yêu thích nhất tại Nhật Bản. Năm 2015, nhóm phát hành album đầu tay "Cry Baby" thông qua hãng Toy\'s Factory, đánh dấu sự chuyển mình từ một ban nhạc độc lập lên một ban nhạc được c', 'https://drive.google.com/uc?id=1U5r_mbmnF2KIW6c_JVq8WLNnZPKQOZA3', NULL),
	('cs2', 'Fifty Fifty', 'Fifty Fifty là nhóm nhạc nữ trực thuộc công ty Attrakt và ra mắt trước công chúng vào ngày 18/11/2022. Nhóm nhạc ban đầu có 04 thành viên gồm Aran (아란), Keena (키나), Saena (새나) và Sio (시오). Cho đến nay, nhóm chỉ mới cho ra mắt hai Album nhưng đã đạt được rất nhiều thành công trong làng giải trí K-Pop.', 'https://drive.google.com/uc?id=15ueDpddJ9KFAJ8WdXcFIQg9ReGvB62ES', NULL),
	('cs3', 'Đen Vâu', 'Nguyễn Đức Cường, thường được biết đến với nghệ danh Đen hay Đen Vâu, là một nam rapper, nhạc sĩ và người dẫn chương trình người Việt Nam. Đen Vâu là "một trong số ít nghệ sĩ thành công từ làn sóng underground và âm nhạc indie" của Việt Nam.', 'https://drive.google.com/uc?id=14O_gskiuueWI0iIXwJWiP1JaKNxb3Alf', NULL),
	('cs4', 'Justatee', 'Nguyễn Thanh Tuấn, thường được biết đến với nghệ danh JustaTee, là một nam ca sĩ, nhạc sĩ sáng tác ca khúc, rapper kiêm nhà sản xuất thu âm người Việt Nam. Là một trong những ca sĩ nổi bật trong dòng nhạc R&B, anh từng giành một giải Cống hiến và giải Zing Music Awards.', 'https://drive.google.com/uc?id=1jnw4Ztrm54nAiQyxSwgJzudacglvGrOy', NULL),
	('cs5', '911', '911 là một nhóm nhạc nam người Anh gồm Lee Brennan, Jimmy Constable và Simon "Spike" Dawbarn. Nhóm được thành lập tại Glasgow vào năm 1995 và ra mắt đĩa đơn đầu tay "Night to Remember" vào tháng 5 năm 1996. Sau đó, họ phát hành album đầu tay The Journey vào tháng 3 năm 1997, đã được chứng nhận Vàng bởi BPI vào tháng 11 năm 1997. 911 phát hành thêm hai album được chứng nhận Bạc, Moving On và There It Is, vào năm 1998 và 1999, liên tiếp. There It Is cũng là đĩa đơn quán quân duy nhất của họtại Vươ', 'https://drive.google.com/uc?id=1AglH5b08LeQyX0WIYlv3gwkrbr9r2OpG', NULL),
	('cs6', 'Avicii', 'Avicii sinh ngày 8/9/1989, tại Stockholm, Thụy Điển. Anh là DJ, nhà sản xuất âm nhạc hàng đầu trên giới. Trong sự nghiệp, tay chơi nhạc 28 tuổi gặt hái được vô số thành công như một giải thưởng âm nhạc Mỹ, một giải Billboard, một giải Echo, một giải iHeartRadio cùng nhiều giải thưởng lớn khác.', 'https://drive.google.com/uc?id=1YKc8xn5JKnPmtVT1QuHR9jAiam78KpXe', NULL),
	('cs7', 'Big Bang', 'Big Bang (cách điệu là BIGBANG), là một nhóm nhạc nam Hàn Quốc được thành lập bởi YG Entertainment, chính thức ra mắt năm 2006. Nhóm gồm 5 thành viên G-Dragon, T.O.P, Taeyang, Daesung và Seungri. Tuy nhiên, vào ngày 11 tháng 3 năm 2019, Seungri rời khỏi ngành giải trí do scandal Burning Sun; ngày 7 tháng 2 năm 2022, T.O.P rời khỏi công ty chủ quản YG; ngày 31 tháng 5 năm 2023, T.O.P đã thông báo trên trang Instagram rằng anh sẽ rời khỏi nhóm nhạc Big Bang sau 17 năm gắn bó để tập trung vào sự ng', 'https://drive.google.com/uc?id=156S6CaKOc0p1tPlvp28kDiJqL4BlG4de', NULL),
	('cs8', 'Black Pink', 'Blackpink (Tiếng Hàn: 블랙핑크; Romaja: Beullaekpingkeu, cách điệu bằng việc viết hoa tất cả các chữ cái hoặc BLɅϽKPIИK) là một nhóm nhạc nữ Hàn Quốc do YG Entertainment thành lập và quản lý vào năm 2016, bao gồm bốn thành viên Jisoo, Jennie, Rosé và Lisa. Nhóm ra mắt vào tháng 8 năm 2016 với album đĩa đơn Square One, trong đó có hai ca khúc "Whistle" và "Boombayah", lần lượt đạt vị trí số một trên bảng xếp hạng Gaon Digital Chart của Hàn Quốc và bảng xếp hạng Billboard World Digital Song Sales.', 'https://drive.google.com/uc?id=1XhWTSPlslWhDitGKhAHQG8lcKKIavY74', NULL),
	('cs9', 'Đàm Vĩnh Hưng', 'Đàm Vĩnh Hưng quê gốc ở Điện Bàn, tỉnh Quảng Nam, ông nội là người có nguồn gốc Việt - Pháp, bà nội có nguồn gốc Phúc Kiến, mẹ là người Quảng Nam.[3] Đàm Vĩnh Hưng còn có một người em ruột. Sinh trưởng tại Sài Gòn trong một gia đình Công giáo dòng, anh thường tham gia các hoạt động của ca đoàn nhà thờ từ nhỏ', 'https://drive.google.com/uc?id=104L65tKXzLe7NoOQyxznwzeOpzlKeRfk', NULL);

-- Dumping structure for table doancoso1.datauser
CREATE TABLE IF NOT EXISTS `datauser` (
  `IDuser` varchar(50) DEFAULT NULL,
  `Username` varchar(50) DEFAULT NULL,
  `imageUrl` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table doancoso1.datauser: ~1 rows (approximately)
INSERT INTO `datauser` (`IDuser`, `Username`, `imageUrl`) VALUES
	(NULL, 'demo', 'file:/D:/divenha.jpg');

-- Dumping structure for table doancoso1.follow
CREATE TABLE IF NOT EXISTS `follow` (
  `idFollow` int(11) NOT NULL AUTO_INCREMENT,
  `idCasi` varchar(50) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  PRIMARY KEY (`idFollow`),
  KEY `FK_follow_nguoidung` (`idUser`),
  KEY `FK_follow_casi` (`idCasi`),
  CONSTRAINT `FK_follow_casi` FOREIGN KEY (`idCasi`) REFERENCES `casi` (`IDcasi`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_follow_nguoidung` FOREIGN KEY (`idUser`) REFERENCES `nguoidung` (`IDuser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table doancoso1.follow: ~2 rows (approximately)
INSERT INTO `follow` (`idFollow`, `idCasi`, `idUser`) VALUES
	(8, 'cs2', 1),
	(9, 'cs1', 1);

-- Dumping structure for table doancoso1.listbaihat
CREATE TABLE IF NOT EXISTS `listbaihat` (
  `IDdanhsach` varchar(50) NOT NULL,
  `Tendanhsach` varchar(50) NOT NULL,
  `imgUrl` varchar(200) DEFAULT NULL,
  `Ngaytao` date DEFAULT NULL,
  `IDuser` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDdanhsach`),
  KEY `FK_listbaihat_nguoidung` (`IDuser`),
  CONSTRAINT `FK_listbaihat_nguoidung` FOREIGN KEY (`IDuser`) REFERENCES `nguoidung` (`IDuser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table doancoso1.listbaihat: ~3 rows (approximately)
INSERT INTO `listbaihat` (`IDdanhsach`, `Tendanhsach`, `imgUrl`, `Ngaytao`, `IDuser`) VALUES
	('g13', 'Ronaldo', 'C:\\Users\\DELL\\Pictures\\f.jpg', '2024-05-15', 1),
	('l39', 'Neymar jr', 'C:\\Users\\DELL\\Pictures\\qrst.jpg', '2024-05-30', 1),
	('v27', 'Messi', 'D:\\945_1683210783_smart-city.jpg', '2024-05-13', 1);

-- Dumping structure for table doancoso1.nguoidung
CREATE TABLE IF NOT EXISTS `nguoidung` (
  `IDuser` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Birthday` date DEFAULT NULL,
  `Gender` varchar(50) DEFAULT NULL,
  `Avatar` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`IDuser`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table doancoso1.nguoidung: ~2 rows (approximately)
INSERT INTO `nguoidung` (`IDuser`, `Username`, `Password`, `Birthday`, `Gender`, `Avatar`) VALUES
	(1, 'demo', '123', '2000-07-12', 'Male', 'file:/D:/divenha.jpg'),
	(2, 'demo1', '123', '1999-09-18', 'Male', NULL);

-- Dumping structure for table doancoso1.thembaihat
CREATE TABLE IF NOT EXISTS `thembaihat` (
  `IDthem` int(11) NOT NULL AUTO_INCREMENT,
  `IDdanhsach` varchar(50) NOT NULL,
  `IDbaihat` varchar(50) NOT NULL,
  PRIMARY KEY (`IDthem`) USING BTREE,
  KEY `FK_thembaihat_baihat` (`IDbaihat`),
  KEY `FK_thembaihat_listbaihat` (`IDdanhsach`),
  CONSTRAINT `FK_thembaihat_baihat` FOREIGN KEY (`IDbaihat`) REFERENCES `baihat` (`IDbaihat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_thembaihat_listbaihat` FOREIGN KEY (`IDdanhsach`) REFERENCES `listbaihat` (`IDdanhsach`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table doancoso1.thembaihat: ~2 rows (approximately)
INSERT INTO `thembaihat` (`IDthem`, `IDdanhsach`, `IDbaihat`) VALUES
	(54, 'g13', '1'),
	(55, 'g13', '4');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
