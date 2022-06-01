/*
  each chat message will store twice, one time user1
  is user and user2 is audience and another time vice versa
  user field is PRIMARY KEY so one version of all chat messages 
  that some user is one side of them (sender or receiver) will
  be stored in one node, so each user can acces his/her chat message
  with specfic user easily:
  SELECT * FROM chat_message WHERE user='user's username' AND audience='audience's username'
  and for search something in all chats:
  SELECT * FROM chat_message WHERE user='user's username' AND body='something'
*/
CREATE TABLE chat_message (
    user text,
    audience text,
    user_is_sender boolean,
    body text,
    time timestamp,
    PRIMARY KEY ((user), time)
) WITH CLUSTERING ORDER BY (time ASC);


CREATE TABLE group_message (
    group text,
    user text,
    body text,
    time timestamp,
    PRIMARY KEY ((group), time)
) WITH CLUSTERING ORDER BY (time ASC);

CREATE TABLE group (
  name text PRIMARY KEY,
  members list<text>
)

/*
  we have two rows in chat table for each chat,
  that in one of them user1 is user and user2 is 
  audience and in the another row it is vice versa
  user field is PRIMARY KEY so all chats of one user
  will be stored in one node while audiences have 
  their vice versa version in another concentrated
  nodes.
*/
CREATE TABLE chat (
  user text,
  audience text,
  PRIMARY KEY ((user))
)

CREATE TABLE user (
  username text PRIMARY KEY,
  groups list<text>
)
