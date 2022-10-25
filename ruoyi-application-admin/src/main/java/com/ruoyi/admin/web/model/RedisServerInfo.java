package com.ruoyi.admin.web.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * redis监控详细信息返回对象
 * @author weibocy
 * https://redis.io/commands/info/
 */
@Data
@ApiModel("redis服务器信息对象")
public class RedisServerInfo {

    @ApiModelProperty(value = "由活跃的碎片整理过程执行的值重新分配的数量")
    private String activeDefragHits;

    @ApiModelProperty(value = "积极进行碎片整理的密钥数")
    private String activeDefragKeyHits;

    @ApiModelProperty(value = "活动碎片整理过程跳过的键数")
    private String activeDefragKeyMisses;

    @ApiModelProperty(value = "活动碎片整理进程启动的中止值重新分配的数量")
    private String activeDefragMisses;

    @ApiModelProperty(value = "activedefrag启用时，这表明碎片整理当前是否处于活动状态，以及它打算使用的 CPU 百分比。")
    private String activeDefragRunning;

    @ApiModelProperty(value = "分配器活动页面中的总字节数，这包括外部碎片。")
    private String allocatorActive;

    @ApiModelProperty(value = "从分配器分配的总字节数，包括内部碎片。通常与usedMemory.")
    private String allocatorAllocated;

    @ApiModelProperty(value = "allocatorFragBytes")
    private String allocatorFragBytes;

    @ApiModelProperty(value = "allocatorActive:和之间的比率allocatorAllocated。这是真正的（外部）碎片度量（不是memFragmentationRatio）。")
    private String allocatorFragRatio;

    @ApiModelProperty(value = "分配器中驻留的总字节数 (RSS)，这包括可以释放到操作系统的页面（通过MEMORY PURGE，或只是等待）。")
    private String allocatorResident;

    @ApiModelProperty(value = "allocatorRssBytes")
    private String allocatorRssBytes;

    @ApiModelProperty(value = "allocatorRssRatio")
    private String allocatorRssRatio;

    @ApiModelProperty(value = "如果服务器正在进行AOF重写操作，那么这个值记录的就是当前重写操作已经耗费的时间（单位是秒）")
    private String aofCurrentRewriteTimeSec;

    @ApiModelProperty(value = "一个标志值，记录了AOF是否处于打开状态")
    private String aofEnabled;

    @ApiModelProperty(value = "一个标志值，记录了最后一次重写AOF文件的结果是成功还是失败")
    private String aofLastBgrewriteStatus;

    @ApiModelProperty(value = "最后一次 AOF 重写操作期间的写时复制内存的大小（以字节为单位）")
    private String aofLastCowSize;

    @ApiModelProperty(value = "记录了最后一次AOF重写操作的耗时")
    private String aofLastRewriteTimeSec;

    @ApiModelProperty(value = "对 AOF 的最后一次写操作的状态")
    private String aofLastWriteStatus;

    @ApiModelProperty(value = "一个标志值，记录了服务器是否正在创建AOF文件")
    private String aofRewriteInProgress;

    @ApiModelProperty(value = "一个标志值，记录了RDB文件创建完之后，是否需要执行预约的AOF重写操作")
    private String aofRewriteScheduled;

    @ApiModelProperty(value = "架构（32 或 64 位）")
    private String archBits;

    @ApiModelProperty(value = "Redis 使用的 Atomicvar API")
    private String atomicvarApi;

    @ApiModelProperty(value = "正在等待阻塞命令（BLPOP、BRPOP、BRPOPLPUSH）的客户端的数量")
    private String blockedClients;

    @ApiModelProperty(value = "当前客户端连接中最大的输入缓冲区")
    private String clientRecentMaxInputBuffer;

    @ApiModelProperty(value = "当前客户端连接中最大的输出缓冲区")
    private String clientRecentMaxOutputBuffer;

    @ApiModelProperty(value = "客户端超时表中的客户端数量")
    private String clientsInTimeoutTable;

    @ApiModelProperty(value = "实例是否启用集群模式")
    private String clusterEnabled;

    @ApiModelProperty(value = "服务器配置的频率设置")
    private String configuredHz;

    @ApiModelProperty(value = "已连接客户端的数量（不包括通过从属服务器连接的客户端）")
    private String connectedClients;

    @ApiModelProperty(value = "连接的slave实例个数")
    private String connectedSlaves;

    @ApiModelProperty(value = "数据库相关的统计信息，0号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db0;

    @ApiModelProperty(value = "数据库相关的统计信息，1号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db1;

    @ApiModelProperty(value = "数据库相关的统计信息，2号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db2;

    @ApiModelProperty(value = "数据库相关的统计信息，3号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db3;

    @ApiModelProperty(value = "数据库相关的统计信息，4号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db4;

    @ApiModelProperty(value = "数据库相关的统计信息，5号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db5;

    @ApiModelProperty(value = "数据库相关的统计信息，6号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db6;

    @ApiModelProperty(value = "数据库相关的统计信息，7号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db7;

    @ApiModelProperty(value = "数据库相关的统计信息，8号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db8;

    @ApiModelProperty(value = "数据库相关的统计信息，9号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db9;

    @ApiModelProperty(value = "数据库相关的统计信息，10号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db10;

    @ApiModelProperty(value = "数据库相关的统计信息，11号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db11;

    @ApiModelProperty(value = "数据库相关的统计信息，12号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db12;

    @ApiModelProperty(value = "数据库相关的统计信息，13号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db13;

    @ApiModelProperty(value = "数据库相关的统计信息，14号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db14;

    @ApiModelProperty(value = "数据库相关的统计信息，15号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db15;

    @ApiModelProperty(value = "因为最大内存容量限制而被驱逐（evict）的键数量")
    private String evictedKeys;

    @ApiModelProperty(value = "服务器可执行文件的路径")
    private String executable;

    @ApiModelProperty(value = "在有效到期周期上花费的累计时间量")
    private String expireCycleCpuMilliseconds;

    @ApiModelProperty(value = "因为过期而被自动删除的数据库键数量")
    private String expiredKeys;

    @ApiModelProperty(value = "过期的数据库键百分比")
    private String expiredStalePerc;

    @ApiModelProperty(value = "活动到期周期提前停止的次数")
    private String expiredTimeCapReachedCount;

    @ApiModelProperty(value = "编译 Redis 时所使用的 GCC 版本")
    private String gccVersion;

    @ApiModelProperty(value = "服务器当前频率设置")
    private String hz;

    @ApiModelProperty(value = "网络每秒的流入速率，以 KB/sec 为单位")
    private String instantaneousInputKbps;

    @ApiModelProperty(value = "服务器每秒中执行的命令数量")
    private String instantaneousOpsPerSec;

    @ApiModelProperty(value = "网络每秒的流出速率，以 KB/sec 为单位")
    private String instantaneousOutputKbps;

    @ApiModelProperty(value = "主线程和 I/O 线程处理的读取事件数")
    private String ioThreadedReadsProcessed;

    @ApiModelProperty(value = "主线程和 I/O 线程处理的写事件数")
    private String ioThreadedWritesProcessed;

    @ApiModelProperty(value = "指示 I/O 线程是否处于活动状态的标志")
    private String ioThreadsActive;

    @ApiModelProperty(value = "查找数据库键成功的次数")
    private String keyspaceHits;

    @ApiModelProperty(value = "查找数据库键失败的次数")
    private String keyspaceMisses;

    @ApiModelProperty(value = "最近一次fork()操作耗费的时间(毫秒)")
    private String latestForkUsec;

    @ApiModelProperty(value = "等待释放的对象数（调用UNLINK或使用ASYNC 选项FLUSHDB的结果）FLUSHALL")
    private String lazyfreePendingObjects;

    @ApiModelProperty(value = "一个标志值，记录了服务器是否正在载入持久化文件")
    private String loading;

    @ApiModelProperty(value = "以分钟为单位进行自增的时钟，用于 LRU 管理")
    private String lruClock;

    @ApiModelProperty(value = "主从同步偏移量,此值如果和上面的offset相同说明主从一致没延迟")
    private String masterReplOffset;

    @ApiModelProperty(value = "Redis 服务器的复制 ID。")
    private String masterReplid;

    @ApiModelProperty(value = "辅助复制 ID，用于故障转移后的 PSYNC。")
    private String masterReplid2;

    @ApiModelProperty(value = "maxmemory配置指令的值")
    private String maxmemory;

    @ApiModelProperty(value = "以人类可读的方式显示maxmemory配置指令的值")
    private String maxmemoryHuman;

    @ApiModelProperty(value = "maxmemory-policy配置指令的值")
    private String maxmemoryPolicy;

    @ApiModelProperty(value = "在编译时指定的， Redis 所使用的内存分配器。可以是 libc 、 jemalloc 或者 tcmalloc")
    private String memAllocator;

    @ApiModelProperty(value = "用于 AOF 和 AOF 重写缓冲区的瞬态内存")
    private String memAofBuffer;

    @ApiModelProperty(value = "正常客户端使用的内存")
    private String memClientsNormal;

    @ApiModelProperty(value = "副本客户端使用的内存 - 从 Redis 7.0 开始，副本缓冲区与复制积压共享内存，因此当副本不触发内存使用量增加时，此字段可以显示 0。")
    private String memClientsSlaves;

    @ApiModelProperty(value = "memFragmentationBytes")
    private String memFragmentationBytes;

    @ApiModelProperty(value = "usedMemoryRss 和 usedMemory 之间的比率，大于10表示内存碎片过多，小于1表示部分内存被操作系统换出到交换空间可能会产生明显延迟")
    private String memFragmentationRatio;

    @ApiModelProperty(value = "使用的内存不计入键逐出。这基本上是瞬态副本和 AOF 缓冲区。")
    private String memNotCountedForEvict;

    @ApiModelProperty(value = "复制使用的内存")
    private String memReplicationBacklog;

    @ApiModelProperty(value = "合并缓存打开的套接字数量？")
    private String migrateCachedSockets;

    @ApiModelProperty(value = "指示模块分叉正在进行的标志")
    private String moduleForkInProgress;

    @ApiModelProperty(value = "在最后一个模块分叉操作期间，写时复制内存的大小（以字节为单位）")
    private String moduleForkLastCowSize;

    @ApiModelProperty(value = "Redis 所使用的事件处理机制")
    private String multiplexingApi;

    @ApiModelProperty(value = "缓存脚本数量？")
    private String numberOfCachedScripts;

    @ApiModelProperty(value = "Redis 服务器的宿主操作系统")
    private String os;

    @ApiModelProperty(value = "服务器进程的 PID")
    private String processId;

    @ApiModelProperty(value = "目前被订阅的频道数量")
    private String pubsubChannels;

    @ApiModelProperty(value = "目前被订阅的模式数量")
    private String pubsubPatterns;

    @ApiModelProperty(value = "一个标志值，记录服务器是否正在创建RDB文件")
    private String rdbBgsaveInProgress;

    @ApiModelProperty(value = "距离最后一次成功创建持久化文件之后，改变了多少个键值")
    private String rdbChangesSinceLastSave;

    @ApiModelProperty(value = "如果服务器正在创建RDB文件，那么这个值记录的就是当前的创建RDB操作已经耗费了多长时间（单位为秒）")
    private String rdbCurrentBgsaveTimeSec;

    @ApiModelProperty(value = "一个标志值，记录了最后一次创建RDB文件的结果是成功还是失败")
    private String rdbLastBgsaveStatus;

    @ApiModelProperty(value = "记录最后一次创建RDB文件耗费的秒数")
    private String rdbLastBgsaveTimeSec;

    @ApiModelProperty(value = "上次 RDB 保存操作期间的写时复制内存大小（以字节为单位）")
    private String rdbLastCowSize;

    @ApiModelProperty(value = "最近一次成功创建RDB文件的UNIX时间")
    private String rdbLastSaveTime;

    @ApiModelProperty(value = "编译id")
    private String redisBuildId;

    @ApiModelProperty(value = "Git dirty flag")
    private String redisGitDirty;

    @ApiModelProperty(value = "Git SHA1")
    private String redisGitSha1;

    @ApiModelProperty(value = "服务器的模式（“单机”、“哨兵”或“集群”）")
    private String redisMode;

    @ApiModelProperty(value = "Redis 服务器版本")
    private String redisVersion;

    @ApiModelProperty(value = "因为最大客户端数量限制而被拒绝的连接请求数量")
    private String rejectedConnections;

    @ApiModelProperty(value = "复制积压缓冲区是否开启")
    private String replBacklogActive;

    @ApiModelProperty(value = "复制缓冲区里偏移量的大小")
    private String replBacklogFirstByteOffset;

    @ApiModelProperty(value = "此值等于 masterReplOffset - replBacklogFirstByteOffset,该值不会超过replBacklogSize的大小")
    private String replBacklogHistlen;

    @ApiModelProperty(value = "复制积压缓冲大小")
    private String replBacklogSize;

    @ApiModelProperty(value = "实例的角色，是master or slave")
    private String role;

    @ApiModelProperty(value = "usedMemoryRss（进程 RSS）和allocatorResident")
    private String rssOverheadBytes;

    @ApiModelProperty(value = "usedMemoryRss: (进程 RSS) 和之间的比率allocatorResident。这包括与分配器或堆无关的 RSS 开销。")
    private String rssOverheadRatio;

    @ApiModelProperty(value = "Redis 服务器的随机标识符（用于 Sentinel 和集群）")
    private String runId;

    @ApiModelProperty(value = "复制 ID 每秒的偏移量")
    private String secondReplOffset;

    @ApiModelProperty(value = "为过期目的而跟踪的数据库键数量（仅适用于可写副本）")
    private String slaveExpiresTrackedKeys;

    @ApiModelProperty(value = "与副本完全重新同步的次数")
    private String syncFull;

    @ApiModelProperty(value = "被拒绝的部分重新同步请求的数量")
    private String syncPartialErr;

    @ApiModelProperty(value = "接受的部分重新同步请求的数量")
    private String syncPartialOk;

    @ApiModelProperty(value = "TCP/IP 监听端口")
    private String tcpPort;

    @ApiModelProperty(value = "服务器已经执行的命令数量")
    private String totalCommandsProcessed;

    @ApiModelProperty(value = "服务器已经接受的连接请求数量")
    private String totalConnectionsReceived;

    @ApiModelProperty(value = "网络流入读取的总字节数")
    private String totalNetInputBytes;

    @ApiModelProperty(value = "网络流出读取的总字节数")
    private String totalNetOutputBytes;

    @ApiModelProperty(value = "处理的读取事件总数")
    private String totalReadsProcessed;

    @ApiModelProperty(value = "Redis 服务器拥有的内存总量")
    private String totalSystemMemory;

    @ApiModelProperty(value = "以人类可读的方式表示Redis 服务器拥有的内存总量")
    private String totalSystemMemoryHuman;

    @ApiModelProperty(value = "处理的写事件总数")
    private String totalWritesProcessed;

    @ApiModelProperty(value = "被跟踪的客户端数量")
    private String trackingClients;

    @ApiModelProperty(value = "正在跟踪的项目数，即每个键的客户数之和")
    private String trackingTotalItems;

    @ApiModelProperty(value = "服务器正在跟踪的数据库键数")
    private String trackingTotalKeys;

    @ApiModelProperty(value = "服务器前缀表中跟踪的前缀数（仅适用于广播模式）")
    private String trackingTotalPrefixes;

    @ApiModelProperty(value = "意外错误回复的数量，即来自 AOF 加载或复制的错误类型")
    private String unexpectedErrorReplies;

    @ApiModelProperty(value = "自 Redis 服务器启动以来，经过的天数")
    private String uptimeInDays;

    @ApiModelProperty(value = "自 Redis 服务器启动以来，经过的秒数")
    private String uptimeInSeconds;

    @ApiModelProperty(value = "Redis服务器耗费的系统CPU")
    private String usedCpuSys;

    @ApiModelProperty(value = "Redis后台进程耗费的系统CPU")
    private String usedCpuSysChildren;

    @ApiModelProperty(value = "Redis服务器耗费的用户CPU")
    private String usedCpuUser;

    @ApiModelProperty(value = "Redis后台进程耗费的用户CPU")
    private String usedCpuUserChildren;

    @ApiModelProperty(value = "由 Redis 分配器分配的内存总量，以字节（byte）为单位")
    private String usedMemory;

    @ApiModelProperty(value = "数据集的大小（以字节为单位）（usedMemoryOverhead减去usedMemory）")
    private String usedMemoryDataset;

    @ApiModelProperty(value = "超出净内存使用的百分比（usedMemory减usedMemoryStartup）")
    private String usedMemoryDatasetPerc;

    @ApiModelProperty(value = "以人类可读的格式返回 Redis 分配的内存总量")
    private String usedMemoryHuman;

    @ApiModelProperty(value = "Lua 引擎所使用的内存大小（以字节为单位）")
    private String usedMemoryLua;

    @ApiModelProperty(value = "以人类可读的格式返回 Lua 引擎所使用的内存大小（以字节为单位）")
    private String usedMemoryLuaHuman;

    @ApiModelProperty(value = "服务器分配用于管理其内部数据结构的所有开销的总和（以字节为单位）")
    private String usedMemoryOverhead;

    @ApiModelProperty(value = "Redis 的内存消耗峰值（以字节为单位）")
    private String usedMemoryPeak;

    @ApiModelProperty(value = "以人类可读的格式返回 Redis 的内存消耗峰值")
    private String usedMemoryPeakHuman;

    @ApiModelProperty(value = "usedMemoryPeak算出来的百分比")
    private String usedMemoryPeakPerc;

    @ApiModelProperty(value = "从操作系统的角度，返回 Redis 已分配的内存总量（俗称常驻集大小）。这个值和top 、 ps 等命令的输出一致。")
    private String usedMemoryRss;

    @ApiModelProperty(value = "以人类可读的格式返回 从操作系统的角度，返回 Redis 已分配的内存总量（俗称常驻集大小）。这个值和top 、 ps 等命令的输出一致。")
    private String usedMemoryRssHuman;

    @ApiModelProperty(value = "缓存 Lua 脚本使用的字节数")
    private String usedMemoryScripts;

    @ApiModelProperty(value = "以人类可读的格式返回 缓存 Lua 脚本使用的字节数")
    private String usedMemoryScriptsHuman;

    @ApiModelProperty(value = "Redis 在启动时消耗的初始内存量（以字节为单位）")
    private String usedMemoryStartup;

}
