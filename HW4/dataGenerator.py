import collections
from random import randrange


# generate users
users_list = []
usernames_file = open('/home/mohammadreza/Documents/CassandraData/usernames.csv')
for line in usernames_file:
    user_list = []
    user_list.append(line[:-1])
    empty_list = []
    user_list.append(empty_list)
    users_list.append(user_list)


# generate groups
groups_list = []
for i in range(20):
    group_name = 'group'+ str(i)
    members_list = []
    for j in range(10):
        randInt = randrange(0, len(users_list))
        members_list.append(users_list[randInt][0])
        users_list[randInt][1].append(group_name)
    group_list = [group_name, members_list]
    groups_list.append(group_list)

groups_file = open('/home/mohammadreza/Documents/CassandraData/groups.csv', 'w')
for group in groups_list:
    members_list_string = str(group[1])
    groups_file.write(group[0] + ',' + members_list_string[1:int(len(members_list_string)-1)] + '\n')


# generate chats
chats_list = []
for i in range(50):
    chat_name = 'chat'+ str(i)
    randInt1 = randrange(0, len(users_list))
    randInt2 = randrange(0, len(users_list))
    user1 = users_list[randInt1][0]
    user2 = users_list[randInt2][0]
    chat_list = [chat_name, user1, user2]
    chats_list.append(chat_list)

chats_file = open('/home/mohammadreza/Documents/CassandraData/chats.csv', 'w')
for chat in chats_list:
    chats_file.write(chat[1] + ',' + chat[2] + '\n')


# generate chat messages
chat_messages_list = []
for i in range(100):
    randInt1 = randrange(0, len(users_list))
    randInt2 = randrange(0, len(users_list))
    from_user = users_list[randInt1][0]
    to_user = users_list[randInt2][0]
    body = 'this is a chat message from ' + from_user + ' to ' + to_user
    time = randrange(0, 100000)
    chat_message_list = [from_user, to_user, body, time]
    chat_messages_list.append(chat_message_list)

chat_messages_file = open('/home/mohammadreza/Documents/CassandraData/chat_messages.csv', 'w')
for chat_message in chat_messages_list:
    chat_messages_file.write(chat_message[0] + ',' + chat_message[1] + ',' + chat_message[2] + ',' + str(chat_message[3]) + '\n')


# generate group messages
group_messages_list = []
for i in range(50):
    randInt1 = randrange(0, len(groups_list))
    group_list = groups_list[randInt1]
    group_name = group_list[0]
    randInt2 = randrange(0, len(users_list))
    from_user = users_list[randInt2][0]
    body = 'this is a group message from ' + from_user + ' to ' + group_name
    time = randrange(0, 100000)
    group_message_list = [from_user, group_name, body, time]
    group_messages_list.append(group_message_list)

group_messages_file = open('/home/mohammadreza/Documents/CassandraData/group_messages.csv', 'w')
for group_message in group_messages_list:
    group_messages_file.write(group_message[0] + ',' + group_message[1] + ',' + group_message[2] + ',' + str(group_message[3]) + '\n')


# write update users on file
users_file = open('/home/mohammadreza/Documents/CassandraData/users.csv', 'w')
for user in users_list:
    groups_list_string = str(user[1])
    users_file.write(user[0] + ', (' + groups_list_string[1:len(groups_list_string)-1] +')'+ '\n')