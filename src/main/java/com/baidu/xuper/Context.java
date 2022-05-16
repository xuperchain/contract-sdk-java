package com.baidu.xuper;

import com.baidu.xuper.contractpb.Contract;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public interface Context {
    // args return call args of a contract call
    public Map<String, byte[]> args();

    //  caller return caller of a contract call
    //  it returns direct caller in in-contract-call, initiator otherwise
    public String caller();

    // authRequire return auth address required to verify a contract call
    // it returns more than one  addresss in multi sign situation
    public List<String> authRequire();

    // putObject put a object into state, all the value is  transaction isolated,you can read the KV pair
    // in the same transaction, you can visit it through newIterator,
    // but you can not read it in other transaction until the transaction is commited
    public void putObject(byte[] key, byte[] value);

    // getObject return a object, see putObject for more details
    public byte[] getObject(byte[] key);

    // deleteObject delete a object, see putObject for more details
    public void deleteObject(byte[] key);

    // newIterator return a range iterator
    // you can use PrefixRange to generate start and limit if you wanna iterate over a specify prefix
    public Iterator<ContractIteratorItem> newIterator(byte[] start, byte[] limit);

    // queryTx return  a transaction by txid
    public Contract.Transaction queryTx(String txid);

    // queryBlock return a block by blockid
    public Contract.Block queryBlock(String blockid);

    // transfer start an in-contract transfer, transfer an amount to a address
    public void transfer(String to, BigInteger amount);

    // transferAmount get transfer amount of an contract call
    public BigInteger transferAmount();

    // call start a in-contract call,module can be wasm|native|evm, dependding on contract you call
    public Response call(String module, String contract, String method, Map<String, byte[]> args);

    // crossQuery start a cross chain query, see https://xuper.baidu.com/n/xuperdoc/ CrossQuery section for more information.
    public Response crossQuery(String uri, Map<String, byte[]> args);
    
    // emitEvent emit a event with name
    public void emitEvent(String name, byte[] body);

    //  emmitJSONEvent emit an event, body will be marshaled using GSON
    public void emitJSONEvent(String name, Object body);

    // log emmit a log entry
    public void log(String msg);
}