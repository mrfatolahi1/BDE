# generate INSERT in group table commands

groups_file = open('/home/mohammadreza/Documents/CassandraData/groups.csv')

for line in groups_file:
    group_list = line.split(',')
    command = 'INSERT INTO group(name, members) VALUES('
    command += '\'' + group_list[0]+ '\''+ ', ['
    for i in range(1, len(group_list) - 1):
        command += group_list[i] + ', '
    command += group_list[len(group_list)-1][:-1]
    command += ']);'
    print(command)

#######################################################################################
print('\n-----------------------------------------------------------------------------------------\n')
#######################################################################################

# generate INSERT in user table commands

groups_file = open('/home/mohammadreza/Documents/CassandraData/users.csv')

for line in groups_file:
    user_list = line.split(', (')
    command = 'INSERT INTO user(username, groups) VALUES('
    command += '\'' + user_list[0]+ '\''+ ', ['
    groups_list = user_list[1][:-1].split(', ')
    for i in range(1, len(groups_list) - 1):
        command += groups_list[i] + ', '
    command += groups_list[len(groups_list)-1][:-1]
    command += ']);'
    print(command)

#######################################################################################
print('\n-----------------------------------------------------------------------------------------\n')
#######################################################################################

# generate INSERT in chat table commands

chats_file = open('/home/mohammadreza/Documents/CassandraData/chats.csv')

for line in chats_file:
    chat_list = line.split(',')
    command1 = 'INSERT INTO chat(user, audience) VALUES('
    command1 += '\'' + chat_list[0]+ '\', '+ '\'' + chat_list[1][:-1] + '\');'
    print(command1)
    command2 = 'INSERT INTO chat(user, audience) VALUES('
    command2 += '\'' + chat_list[1][:-1]+ '\', '+ '\'' + chat_list[0] + '\');'
    print(command2)

#######################################################################################
print('\n-----------------------------------------------------------------------------------------\n')
#######################################################################################

# generate INSERT in chat_message table commands

chat_messages_file = open('/home/mohammadreza/Documents/CassandraData/chat_messages.csv')

for line in chat_messages_file:
    chat_message_list = line.split(',')
    command1 = 'INSERT INTO chat_message(user, audience,user_is_sender, body, time) VALUES('
    command1 += '\''+chat_message_list[0]+'\', ' +'\''+chat_message_list[1]+'\', ' +'True'+', ' +'\''+chat_message_list[2]+'\', ' + '\''+chat_message_list[3][:-1]+'\'); '
    print(command1)
    command1 = 'INSERT INTO chat_message(user, audience,user_is_sender, body, time) VALUES('
    command1 += '\''+chat_message_list[1]+'\', ' +'\''+chat_message_list[0]+'\', ' +'False'+', ' +'\''+chat_message_list[2]+'\', ' + '\''+chat_message_list[3][:-1]+'\'); '
    print(command1)

#######################################################################################
print('\n-----------------------------------------------------------------------------------------\n')
#######################################################################################

# generate INSERT in group_message table commands

chat_messages_file = open('/home/mohammadreza/Documents/CassandraData/group_messages.csv')

for line in chat_messages_file:
    command = 'INSERT INTO group_message(group, user, body, time) VALUES('
    group_message_list = line.split(',')
    command += '\''+group_message_list[1]+'\', ' +'\''+group_message_list[0]+'\', ' '\''+group_message_list[2]+'\', ' + '\''+group_message_list[3][:-1]+'\'); '
    print(command)
