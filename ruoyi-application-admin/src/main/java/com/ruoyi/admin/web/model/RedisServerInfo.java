package com.ruoyi.admin.web.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * redis监控详细信息返回对象
 * @author weibocy
 * https://redis.io/commands/info/
 */
@Data
@Schema(description = "redis服务器信息对象")
public class RedisServerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "由活跃的碎片整理过程执行的值重新分配的数量")
    private String activeDefragHits;

    @Schema(description = "积极进行碎片整理的密钥数")
    private String activeDefragKeyHits;

    @Schema(description = "活动碎片整理过程跳过的键数")
    private String activeDefragKeyMisses;

    @Schema(description = "活动碎片整理进程启动的中止值重新分配的数量")
    private String activeDefragMisses;

    @Schema(description = "activedefrag启用时，这表明碎片整理当前是否处于活动状态，以及它打算使用的 CPU 百分比。")
    private String activeDefragRunning;

    @Schema(description = "分配器活动页面中的总字节数，这包括外部碎片。")
    private String allocatorActive;

    @Schema(description = "从分配器分配的总字节数，包括内部碎片。通常与usedMemory.")
    private String allocatorAllocated;

    @Schema(description = "allocatorFragBytes")
    private String allocatorFragBytes;

    @Schema(description = "allocatorActive:和之间的比率allocatorAllocated。这是真正的（外部）碎片度量（不是memFragmentationRatio）。")
    private String allocatorFragRatio;

    @Schema(description = "分配器中驻留的总字节数 (RSS)，这包括可以释放到操作系统的页面（通过MEMORY PURGE，或只是等待）。")
    private String allocatorResident;

    @Schema(description = "allocatorRssBytes")
    private String allocatorRssBytes;

    @Schema(description = "allocatorRssRatio")
    private String allocatorRssRatio;

    @Schema(description = "如果服务器正在进行AOF重写操作，那么这个值记录的就是当前重写操作已经耗费的时间（单位是秒）")
    private String aofCurrentRewriteTimeSec;

    @Schema(description = "一个标志值，记录了AOF是否处于打开状态")
    private String aofEnabled;

    @Schema(description = "一个标志值，记录了最后一次重写AOF文件的结果是成功还是失败")
    private String aofLastBgrewriteStatus;

    @Schema(description = "最后一次 AOF 重写操作期间的写时复制内存的大小（以字节为单位）")
    private String aofLastCowSize;

    @Schema(description = "记录了最后一次AOF重写操作的耗时")
    private String aofLastRewriteTimeSec;

    @Schema(description = "对 AOF 的最后一次写操作的状态")
    private String aofLastWriteStatus;

    @Schema(description = "一个标志值，记录了服务器是否正在创建AOF文件")
    private String aofRewriteInProgress;

    @Schema(description = "一个标志值，记录了RDB文件创建完之后，是否需要执行预约的AOF重写操作")
    private String aofRewriteScheduled;

    @Schema(description = "架构（32 或 64 位）")
    private String archBits;

    @Schema(description = "Redis 使用的 Atomicvar API")
    private String atomicvarApi;

    @Schema(description = "正在等待阻塞命令（BLPOP、BRPOP、BRPOPLPUSH）的客户端的数量")
    private String blockedClients;

    @Schema(description = "当前客户端连接中最大的输入缓冲区")
    private String clientRecentMaxInputBuffer;

    @Schema(description = "当前客户端连接中最大的输出缓冲区")
    private String clientRecentMaxOutputBuffer;

    @Schema(description = "客户端超时表中的客户端数量")
    private String clientsInTimeoutTable;

    @Schema(description = "实例是否启用集群模式")
    private String clusterEnabled;

    @Schema(description = "服务器配置的频率设置")
    private String configuredHz;

    @Schema(description = "已连接客户端的数量（不包括通过从属服务器连接的客户端）")
    private String connectedClients;

    @Schema(description = "连接的slave实例个数")
    private String connectedSlaves;

    @Schema(description = "数据库相关的统计信息，0号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db0;

    @Schema(description = "数据库相关的统计信息，1号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db1;

    @Schema(description = "数据库相关的统计信息，2号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db2;

    @Schema(description = "数据库相关的统计信息，3号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db3;

    @Schema(description = "数据库相关的统计信息，4号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db4;

    @Schema(description = "数据库相关的统计信息，5号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db5;

    @Schema(description = "数据库相关的统计信息，6号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db6;

    @Schema(description = "数据库相关的统计信息，7号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db7;

    @Schema(description = "数据库相关的统计信息，8号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db8;

    @Schema(description = "数据库相关的统计信息，9号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db9;

    @Schema(description = "数据库相关的统计信息，10号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db10;

    @Schema(description = "数据库相关的统计信息，11号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db11;

    @Schema(description = "数据库相关的统计信息，12号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db12;

    @Schema(description = "数据库相关的统计信息，13号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db13;

    @Schema(description = "数据库相关的统计信息，14号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db14;

    @Schema(description = "数据库相关的统计信息，15号数据库有多少个键、已经被删除的过期键数量、以及带有生存期的key的数量")
    private String db15;

    @Schema(description = "因为最大内存容量限制而被驱逐（evict）的键数量")
    private String evictedKeys;

    @Schema(description = "服务器可执行文件的路径")
    private String executable;

    @Schema(description = "在有效到期周期上花费的累计时间量")
    private String expireCycleCpuMilliseconds;

    @Schema(description = "因为过期而被自动删除的数据库键数量")
    private String expiredKeys;

    @Schema(description = "过期的数据库键百分比")
    private String expiredStalePerc;

    @Schema(description = "活动到期周期提前停止的次数")
    private String expiredTimeCapReachedCount;

    @Schema(description = "编译 Redis 时所使用的 GCC 版本")
    private String gccVersion;

    @Schema(description = "服务器当前频率设置")
    private String hz;

    @Schema(description = "网络每秒的流入速率，以 KB/sec 为单位")
    private String instantaneousInputKbps;

    @Schema(description = "服务器每秒中执行的命令数量")
    private String instantaneousOpsPerSec;

    @Schema(description = "网络每秒的流出速率，以 KB/sec 为单位")
    private String instantaneousOutputKbps;

    @Schema(description = "主线程和 I/O 线程处理的读取事件数")
    private String ioThreadedReadsProcessed;

    @Schema(description = "主线程和 I/O 线程处理的写事件数")
    private String ioThreadedWritesProcessed;

    @Schema(description = "指示 I/O 线程是否处于活动状态的标志")
    private String ioThreadsActive;

    @Schema(description = "查找数据库键成功的次数")
    private String keyspaceHits;

    @Schema(description = "查找数据库键失败的次数")
    private String keyspaceMisses;

    @Schema(description = "最近一次fork()操作耗费的时间(毫秒)")
    private String latestForkUsec;

    @Schema(description = "等待释放的对象数（调用UNLINK或使用ASYNC 选项FLUSHDB的结果）FLUSHALL")
    private String lazyfreePendingObjects;

    @Schema(description = "一个标志值，记录了服务器是否正在载入持久化文件")
    private String loading;

    @Schema(description = "以分钟为单位进行自增的时钟，用于 LRU 管理")
    private String lruClock;

    @Schema(description = "主从同步偏移量,此值如果和上面的offset相同说明主从一致没延迟")
    private String masterReplOffset;

    @Schema(description = "Redis 服务器的复制 ID。")
    private String masterReplid;

    @Schema(description = "辅助复制 ID，用于故障转移后的 PSYNC。")
    private String masterReplid2;

    @Schema(description = "maxmemory配置指令的值")
    private String maxmemory;

    @Schema(description = "以人类可读的方式显示maxmemory配置指令的值")
    private String maxmemoryHuman;

    @Schema(description = "maxmemory-policy配置指令的值")
    private String maxmemoryPolicy;

    @Schema(description = "在编译时指定的， Redis 所使用的内存分配器。可以是 libc 、 jemalloc 或者 tcmalloc")
    private String memAllocator;

    @Schema(description = "用于 AOF 和 AOF 重写缓冲区的瞬态内存")
    private String memAofBuffer;

    @Schema(description = "正常客户端使用的内存")
    private String memClientsNormal;

    @Schema(description = "副本客户端使用的内存 - 从 Redis 7.0 开始，副本缓冲区与复制积压共享内存，因此当副本不触发内存使用量增加时，此字段可以显示 0。")
    private String memClientsSlaves;

    @Schema(description = "memFragmentationBytes")
    private String memFragmentationBytes;

    @Schema(description = "usedMemoryRss 和 usedMemory 之间的比率，大于10表示内存碎片过多，小于1表示部分内存被操作系统换出到交换空间可能会产生明显延迟")
    private String memFragmentationRatio;

    @Schema(description = "使用的内存不计入键逐出。这基本上是瞬态副本和 AOF 缓冲区。")
    private String memNotCountedForEvict;

    @Schema(description = "复制使用的内存")
    private String memReplicationBacklog;

    @Schema(description = "合并缓存打开的套接字数量？")
    private String migrateCachedSockets;

    @Schema(description = "指示模块分叉正在进行的标志")
    private String moduleForkInProgress;

    @Schema(description = "在最后一个模块分叉操作期间，写时复制内存的大小（以字节为单位）")
    private String moduleForkLastCowSize;

    @Schema(description = "Redis 所使用的事件处理机制")
    private String multiplexingApi;

    @Schema(description = "缓存脚本数量？")
    private String numberOfCachedScripts;

    @Schema(description = "Redis 服务器的宿主操作系统")
    private String os;

    @Schema(description = "服务器进程的 PID")
    private String processId;

    @Schema(description = "目前被订阅的频道数量")
    private String pubsubChannels;

    @Schema(description = "目前被订阅的模式数量")
    private String pubsubPatterns;

    @Schema(description = "一个标志值，记录服务器是否正在创建RDB文件")
    private String rdbBgsaveInProgress;

    @Schema(description = "距离最后一次成功创建持久化文件之后，改变了多少个键值")
    private String rdbChangesSinceLastSave;

    @Schema(description = "如果服务器正在创建RDB文件，那么这个值记录的就是当前的创建RDB操作已经耗费了多长时间（单位为秒）")
    private String rdbCurrentBgsaveTimeSec;

    @Schema(description = "一个标志值，记录了最后一次创建RDB文件的结果是成功还是失败")
    private String rdbLastBgsaveStatus;

    @Schema(description = "记录最后一次创建RDB文件耗费的秒数")
    private String rdbLastBgsaveTimeSec;

    @Schema(description = "上次 RDB 保存操作期间的写时复制内存大小（以字节为单位）")
    private String rdbLastCowSize;

    @Schema(description = "最近一次成功创建RDB文件的UNIX时间")
    private String rdbLastSaveTime;

    @Schema(description = "编译id")
    private String redisBuildId;

    @Schema(description = "Git dirty flag")
    private String redisGitDirty;

    @Schema(description = "Git SHA1")
    private String redisGitSha1;

    @Schema(description = "服务器的模式（“单机”、“哨兵”或“集群”）")
    private String redisMode;

    @Schema(description = "Redis 服务器版本")
    private String redisVersion;

    @Schema(description = "因为最大客户端数量限制而被拒绝的连接请求数量")
    private String rejectedConnections;

    @Schema(description = "复制积压缓冲区是否开启")
    private String replBacklogActive;

    @Schema(description = "复制缓冲区里偏移量的大小")
    private String replBacklogFirstByteOffset;

    @Schema(description = "此值等于 masterReplOffset - replBacklogFirstByteOffset,该值不会超过replBacklogSize的大小")
    private String replBacklogHistlen;

    @Schema(description = "复制积压缓冲大小")
    private String replBacklogSize;

    @Schema(description = "实例的角色，是master or slave")
    private String role;

    @Schema(description = "usedMemoryRss（进程 RSS）和allocatorResident")
    private String rssOverheadBytes;

    @Schema(description = "usedMemoryRss: (进程 RSS) 和之间的比率allocatorResident。这包括与分配器或堆无关的 RSS 开销。")
    private String rssOverheadRatio;

    @Schema(description = "Redis 服务器的随机标识符（用于 Sentinel 和集群）")
    private String runId;

    @Schema(description = "复制 ID 每秒的偏移量")
    private String secondReplOffset;

    @Schema(description = "为过期目的而跟踪的数据库键数量（仅适用于可写副本）")
    private String slaveExpiresTrackedKeys;

    @Schema(description = "与副本完全重新同步的次数")
    private String syncFull;

    @Schema(description = "被拒绝的部分重新同步请求的数量")
    private String syncPartialErr;

    @Schema(description = "接受的部分重新同步请求的数量")
    private String syncPartialOk;

    @Schema(description = "TCP/IP 监听端口")
    private String tcpPort;

    @Schema(description = "服务器已经执行的命令数量")
    private String totalCommandsProcessed;

    @Schema(description = "服务器已经接受的连接请求数量")
    private String totalConnectionsReceived;

    @Schema(description = "网络流入读取的总字节数")
    private String totalNetInputBytes;

    @Schema(description = "网络流出读取的总字节数")
    private String totalNetOutputBytes;

    @Schema(description = "处理的读取事件总数")
    private String totalReadsProcessed;

    @Schema(description = "Redis 服务器拥有的内存总量")
    private String totalSystemMemory;

    @Schema(description = "以人类可读的方式表示Redis 服务器拥有的内存总量")
    private String totalSystemMemoryHuman;

    @Schema(description = "处理的写事件总数")
    private String totalWritesProcessed;

    @Schema(description = "被跟踪的客户端数量")
    private String trackingClients;

    @Schema(description = "正在跟踪的项目数，即每个键的客户数之和")
    private String trackingTotalItems;

    @Schema(description = "服务器正在跟踪的数据库键数")
    private String trackingTotalKeys;

    @Schema(description = "服务器前缀表中跟踪的前缀数（仅适用于广播模式）")
    private String trackingTotalPrefixes;

    @Schema(description = "意外错误回复的数量，即来自 AOF 加载或复制的错误类型")
    private String unexpectedErrorReplies;

    @Schema(description = "自 Redis 服务器启动以来，经过的天数")
    private String uptimeInDays;

    @Schema(description = "自 Redis 服务器启动以来，经过的秒数")
    private String uptimeInSeconds;

    @Schema(description = "Redis服务器耗费的系统CPU")
    private String usedCpuSys;

    @Schema(description = "Redis后台进程耗费的系统CPU")
    private String usedCpuSysChildren;

    @Schema(description = "Redis服务器耗费的用户CPU")
    private String usedCpuUser;

    @Schema(description = "Redis后台进程耗费的用户CPU")
    private String usedCpuUserChildren;

    @Schema(description = "由 Redis 分配器分配的内存总量，以字节（byte）为单位")
    private String usedMemory;

    @Schema(description = "数据集的大小（以字节为单位）（usedMemoryOverhead减去usedMemory）")
    private String usedMemoryDataset;

    @Schema(description = "超出净内存使用的百分比（usedMemory减usedMemoryStartup）")
    private String usedMemoryDatasetPerc;

    @Schema(description = "以人类可读的格式返回 Redis 分配的内存总量")
    private String usedMemoryHuman;

    @Schema(description = "Lua 引擎所使用的内存大小（以字节为单位）")
    private String usedMemoryLua;

    @Schema(description = "以人类可读的格式返回 Lua 引擎所使用的内存大小（以字节为单位）")
    private String usedMemoryLuaHuman;

    @Schema(description = "服务器分配用于管理其内部数据结构的所有开销的总和（以字节为单位）")
    private String usedMemoryOverhead;

    @Schema(description = "Redis 的内存消耗峰值（以字节为单位）")
    private String usedMemoryPeak;

    @Schema(description = "以人类可读的格式返回 Redis 的内存消耗峰值")
    private String usedMemoryPeakHuman;

    @Schema(description = "usedMemoryPeak算出来的百分比")
    private String usedMemoryPeakPerc;

    @Schema(description = "从操作系统的角度，返回 Redis 已分配的内存总量（俗称常驻集大小）。这个值和top 、 ps 等命令的输出一致。")
    private String usedMemoryRss;

    @Schema(description = "以人类可读的格式返回 从操作系统的角度，返回 Redis 已分配的内存总量（俗称常驻集大小）。这个值和top 、 ps 等命令的输出一致。")
    private String usedMemoryRssHuman;

    @Schema(description = "缓存 Lua 脚本使用的字节数")
    private String usedMemoryScripts;

    @Schema(description = "以人类可读的格式返回 缓存 Lua 脚本使用的字节数")
    private String usedMemoryScriptsHuman;

    @Schema(description = "Redis 在启动时消耗的初始内存量（以字节为单位）")
    private String usedMemoryStartup;

}
