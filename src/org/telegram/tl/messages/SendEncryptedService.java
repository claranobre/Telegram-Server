/*
 *     This file is part of Telegram Server
 *     Copyright (C) 2015  Aykut Alparslan KOÇ
 *
 *     Telegram Server is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Telegram Server is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.telegram.tl.messages;

import org.telegram.mtproto.ProtocolBuffer;
import org.telegram.tl.*;

public class SendEncryptedService extends TLObject {

    public static final int ID = 852769188;

    public TLInputEncryptedChat peer;
    public long random_id;
    public byte[] data;

    public SendEncryptedService() {
    }

    public SendEncryptedService(TLInputEncryptedChat peer, long random_id, byte[] data){
        this.peer = peer;
        this.random_id = random_id;
        this.data = data;
    }

    @Override
    public void deserialize(ProtocolBuffer buffer) {
        peer = (TLInputEncryptedChat) buffer.readTLObject(APIContext.getInstance());
        random_id = buffer.readLong();
        data = buffer.readBytes();
    }

    @Override
    public ProtocolBuffer serialize() {
        ProtocolBuffer buffer = new ProtocolBuffer(32);
        serializeTo(buffer);
        return buffer;
    }

    @Override
    public void serializeTo(ProtocolBuffer buff) {
        buff.writeInt(getConstructor());
        buff.writeTLObject(peer);
        buff.writeLong(random_id);
        buff.writeBytes(data);
    }

    public int getConstructor() {
        return ID;
    }
}