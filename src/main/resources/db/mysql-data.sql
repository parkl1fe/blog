INSERT INTO user (avatar, username, password, role) VALUES
                 ('none', 'Raj',    'xxx',    'ADMIN'),
                 ('none', 'Sheldon','yyy',    'BLOGGER'),
                 ('none', 'Howard', 'hhh',    'BLOGGER');

INSERT INTO article (user_id, created, 			      title,    description,    content) VALUES
					(1, 	  '2020-01-01 10:10:10', 'title1', 'description1', 'content1'),
					(1, 	  '2020-02-02 20:20:20', 'title2', 'description2', 'content2'),
                    (3, 	  '2020-03-03 13:30:30', 'title3', 'description3', 'content3'),
					(2, 	  '2020-04-04 14:40:40', 'title4', 'description4', 'content4');

INSERT INTO article_stats  (views, article_id, score) VALUES
						   (0,	   1,			100),
						   (545,   2,			5500),
						   (44,    3,			4400),
						   (34,    4,			3425);

INSERT INTO comment (article_id, user_id, created, 				 content) VALUES
					(1, 		 1,		  '2020-01-01 10:10:10', 'content1'),
                    (1, 		 2,		  '2020-02-01 10:10:10', 'content2'),
                    (2, 		 3,		  '2020-03-01 10:10:10', 'content3'),
                    (2, 		 2,		  '2020-04-01 10:10:10', 'content4'),
                    (3, 		 3,		  '2020-05-01 10:10:10', 'content5'),
                    (4, 		 1,		  '2020-06-01 10:10:10', 'content6');
