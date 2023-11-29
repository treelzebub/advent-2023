package net.treelzebub.hohoho.lastyear


private val CHARS = listOf(
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')

object Day3 {

    fun go() {
        part1()
        part2()
    }

    private fun part1() {
        val list = input.split("\n").map { split(it) }
        val priorities = list.map { priority(charInBoth(it)) }
        val sum = priorities.sum()
        println("Priorities = $sum")
    }

    private fun split(str: String): Pair<String, String> {
        val midpoint = (str.length / 2) - 1
        val one = str.substring(0..midpoint)
        val two = str.substring((midpoint + 1)..str.lastIndex)
        return one to two
    }

    private fun charInBoth(pair: Pair<String, String>): Char {
        var char: Char = ' '
        pair.first.forEach {
            if (it in pair.second) char = it
        }
        return char
    }

    private fun priority(char: Char): Int {
        val lowerValue = CHARS.indexOf(char.lowercase().first()) + 1
        return if (char.isUpperCase()) lowerValue + 26 else lowerValue
    }

    private fun part2() {
        val list = input.split("\n").toMutableList()
        val total = byThrees(list)
        println("Part 2 Total is: $total")
    }

    // return char shared by all given strings
    private fun charingIsCaring(list: List<String>): Char {
        var char: Char = ' '
        list.first().forEach {
            if (it in list[1] && it in list[2]) char = it
        }
        return char
    }

    private fun byThrees(list: List<String>): Int {
        var temp = list
        var total = 0
        while (temp.isNotEmpty()) {
            val char = charingIsCaring(temp.take(3))
            total += priority(char)
            temp = temp.drop(3)
        }
        return total
    }
}

private val test = """
    vJrwpWtwJgWrhcsFMMfFFhFp
    jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
    PmmdzqPrVvPwwTWBwg
    wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
    ttgJtRGJQctTZtZT
    CrZsJsPPZsGzwwsLwLmpwMDw
""".trimIndent()

private val input = """
    GbccTtTSGGbgrcWBGGrdgTnVQnCmNpCJlNnNPVfClcnN
    vMzvZhzhwDLVmQnClwwNQp
    FRsZFzjQFsqRzRRjDZbdtTgdHBBWGrdBdHHs
    HCLTmbCLgzNBNPSSlT
    JJGMWRJMrrdwWWGjGWMLRGLjBzNQsBzPPfflzDPBsBffDrQz
    pwJdLMjdMddWjLtwZWMMwGtHhnvnCnhvqVFFZnvbgbqVCZ
    tvMCDCSVVvDDBQFRbqWMMsWgFWgc
    BLLPTpBmfLPrHLLfLsbhRqbzRRcRHgqssR
    dfdNLmPTdNZmZdZPfpmTJLPPSvQjtSGVwQSDJSjSwDQBVCGw
    wZWTWNFqzwZbWNpSgGMVMtTHsgGs
    nlnPnPvLQjzdtsjBHBMMGSHg
    LdnrrLnhRdLLmLDRPvmdQnJDJWNqcCqZJZqfFqfcfzcq
    vPTbfWggzvGVqjsVqV
    dDcJHZcZHmMFQQMshsjcRqVChjNtqh
    dDHJDmFnrJmQFnBdMdQHJdZZlWTTPPjTLWbTzLWlTTfwjzBT
    VfmDHDfZzfLcZLLLHBFQtRJTcdjgdTgFjjtR
    WRPhMlGSshPRGgvFMtddTjjCQt
    SPhWPsbNWShsWllswGpzLmzfZwmZfqLVRrDBZB
    MtZgRgJJbbGjgDDgbjRjRbGcNdWwncBFdLBBMhFBQwnWnw
    vlpsNVsCzzfHpvTpzlSSSvppcQdfnwnWhndWndwfQLhnhcFQ
    pCsCCTHVvSzvPvHvzpPVTVHHRJJDgJZjJjqNmjZmDtJRZPNR
    GTGTbhhPjJJjBhhZsGzmfHSNsmHHgSdL
    FcFpMDFDRFfsRHSjmLjR
    CpFjqcCjwjnpwhTPTWBQZZTb
    zdzzwDlnTDQQQQnqQqqsFqnrSBSprbpjNJJBJPPdpJfbZb
    hgMcRVGMtHgRcNSPrpfgfjJpBp
    CLRHVHhtvtvGPWFFDqPDLnqP
    jssjjjHCSGCMNJTWWCJT
    DvcvBtVrrDSNWcMfLRfM
    ppDZSpBhBvBmvDHnFsHHPnGPGbQp
    VVVLsLWnjVVBGgScjtBjjLLgmJdPmJmfmhmGmmmNmJGhPNJP
    QbqlZqQTZvMHmshvFhPfffFp
    CsTRrQrwbCbrZqQTQlRjtDBSBtwBjgVWLBtgBt
    FLsSFRTPscHZmGRGGc
    npNNptgttCNpgLbnQMgnQnMNqVhGqZrmrmmqrmcrqmCVZwqH
    WMWbtpjLgnLNvSfPPzjvjPdv
    FcFFhZlhlMrHlSFSrHZMJZSVmmLmVLLCsBtLBCzCBVDRcV
    PMGPbndvGfGstLzDCmLB
    NwjPqdvPpvgddqgwHrZhJlTlThpWJMZp
    SdjStScTWTwwvwwfjRhQPQQQDlLBGpLrPrLrLc
    gCqJbNsVsNMgzMJnnqzNlCLGGlZZPrLLlLLtpPDl
    JsFJFMmbJqqnJbhSHdjWwjtmHWvS
    zBFDGGbNzDWRbDccsWslHlWWsJcS
    zMVqTwzPfVfVMwmlcZTZZlSmTlmc
    MrMvMCnrMVMCPrPnDFFGgQdFgRdzznbd
    rJtJnrnSShJgcCsjjNNMSSDzRmzm
    HWDWPBPDBfFVBffqplvlmNlQllvzQNQqlN
    FVGHHFVbwBpBPwFFGfBpHVDgrcJCbZcLdCgtcCcJLJrd
    MGHGGFFqbFTGmFwLmQsQflFN
    WcvBdpjhdZdNwdZwLZ
    vWtgVcpvjthtNcjntDhhpSJMSqHzqTzqCVHTSqHPGT
    cVHZfjfZMcrSDQMJRCBCQw
    PtGddtslsWQDBdwCDDdw
    WsvTFnshPTGhGhhlPNGTCnsjgFVfmgfHZfVHgcHZVVFmFV
    JFFqfJBgrHBffVHlsBFqfWNgjTtztNnttWWvWNwzwt
    hGZbcBcZZSQmZLQRTbvNttTzjtTvbpzv
    cmmhRchPZhZSSmdmGPDDdJdBVMffHlqlslfF
    qWwTNwNHMHNNMRqMdRMQQMHLmmvzrTmrzPvzJvZvZlvzjZ
    FphBpnBhVBSFvLljzZPpmrPL
    nGsBbssbcbdlwggdNl
    RLSRTLSFFFLPSWpzzTJdzsQpbd
    DvqqcwVMDDcfrrnwDcwnvCdpQQphJhJjhdhpzsJhMQ
    fGcvDZffcGGZDHGrGrtJRPlPmJSlPLRgNBHg
    QlFFmGQFDQrrWlRlWGrnQVCLNvvPwLCwBvCcCcJCLCCm
    tHtfsjSMCNPwzvCf
    MjqStqMHsMSgjShjTttgphsTlrbDWGDrGlRTlNDbQrQRWRbD
    QbChcCJCbHQCjbGCjQfsdsrtTqrfTLrcFftd
    DwRzVzzZnzZRwvgRhRWtqsLLWtRdqLLdqd
    NMwMzBVVPPSGQhBl
    ttTPHWdrJjCdjnFMtLLtLNvQltLh
    pSDBwZRBBsgfDGGsGpBVMFPQQlFMFQQFQfNvLNfF
    PsppZzBVzwgDwBwwgpSSBssWjqrdCnjjHdmCTHznWCJJWn
    WcdHdPcdZrLPDPBQDg
    pMjMMqfmJlqNflMlFNRfLBwnLzTTTDJJwDTTGTLJ
    hlhbqpbNNbVVdbZtSB
    RDBWGRDnzBWBJDNBttSLlclldtQQcTTLFF
    rTPVjZZsCZrVhdFMcgLgwFSgQh
    PsjHVffbsTCHrCvTPfDJGJHNzzNJWnnnmzDB
    LQdFgTLdQjVsQFTRBjMZrmBjWGMGSW
    flvJJlJpbNnppCpMGGfBBZSZRfFmGr
    NNbNDNlbDpHlbDDplvzvnCbzqsQPFQsTTccsqdQqLgdHLwQw
    DnGDNDTFdFwDzCZZRmhThCRRRv
    SgrPLrrLsBPbHBCmtVZVCdCcctHH
    rSbgBrsqgsPppMBqfpPsLpPGlNGGDwNFNJWDldlllDwJMG
    PWbvNWvpvJPnWDGqDjDczj
    QwfFFVVQSMlDlQfFZhsHrBrhHHTcjnczqjzqrG
    mwMSgfmDmSSFgfFNbmLpbRbJbvbRpC
    lsggLLLDGldGTGBBhNTCwRwVnJnNCCnbRV
    QQpWrpHtrHrpNRRJNtfbJCVR
    PvQQFPzccvBglclNscls
    NsszMMNGWLcWBhMF
    gTtwvbqfnDTdpvqDftpnnntDZvLFQFBLmRWFRhJZJhLLBRQB
    DwrpDbngprPWGllSNrSS
    nCqdLPZPMMZLNvtGhRmGhGPmtW
    TSrVZVSZVwFTgSVtrtchvWRRrtWtcr
    gjbjBjgTjfgfVfHHppBLMnqLMDnqClsZJLLD
    hrqShCPCpHHBVBGWQFVQGFGnzQDf
    tgvZsbwsbcMbRsgccjDGFvGFfWJLLzFFQJ
    TTZmMcgmbmWZMctbbtsHrrqqSHHrCrPBBSCPrT
    HHHNZLGLpBpRSvWlGlqhPghqDGnnFr
    QCNCMTJdjMjdjsQTbdQmmCQDngFqnggPFcPcnPFcDqcbDn
    MNJfzNsfJdJjdzwMNjjTJttSHVStRtZVwHvWRWtZHt
    DSbvDdDbbwHgCSgZPwpbPgmTTJhsTTChqTJssQssFmJJ
    zzjMNNGMMRcNNhvnvFqmtJJv
    WWffvlVrcGzGlcjLvfrVRLHgHgpDPSbPpwwHbWbBbPPH
    FCCjjFlFtCjzlpTHtJsQTTcpTT
    DWLhWSgDWWdSWLwmmpHHQTHcBTBvvwHvHl
    mgGRhrLLgWqnjrfCNlzP
    cLsslBlsqNNTHlTVNbLZZLRCQbZZdQdpbP
    JGfJhhwfwBBSJPRdZddpZRQbfR
    hWmWGgDhJrFhBcWsssWHvHll
    lmmvlJFtMHFtQzVSRbPGzLJRgG
    BcTcrNBrrwwqDBqNqwcrhLpLPVzRhPRPPPgSGVPLbS
    TrDqcnsTcsmnvHtdGtMW
    CcnDQpSDcnFcPBrmbPQGBsGB
    gCtCfRZTBWbjPRbr
    qgvHqgJhMfZTtvHgfTghJgMJDpdppFSLLCcSDvLLcdDwvLcw
    ffFgGRMWSTGcnDgllDDpDp
    dvSdHBrVSLNVLjdlsllcsDqpsZ
    SHHHNrLJLvtNQJVvmMfGRGGRCJWJRwzWMh
    JNpNDfDBDHVzwHHzpzBWVBPsvsFNCbmbqsFFNsjCmvsmNC
    rnnrtLhnrrQZMvtFbWmqtllcFb
    GQRdGQLLhMSQhZLZdgdwwHzPDzSVWzVDwJDVpz
    LdcGjgdcrMDSFGVfnnGG
    HNsCCQFCPvFFBJnnSBJVfDVJwf
    HHFRqHPpNppmQPcpLjzrdgtbgztT
    GlZZbclGZsDvlGhsShRnCnMQtjtQjnCQsQRM
    PggFVcdFNFNNVVFLPdPdrwpWBMMnqMpnttJMnjMnQqtqQtqq
    FdgcdcLwfTmSGTmhlbzG
    RGvhGrLhhRhlpChZrGSprBdPPHJJSBgSSHqBWBBffH
    mQmjmwtTMTVLzHnTPWffPHHJBf
    jMmmwMcVcFLFrlRshZbCrF
    SnNgNgBlNZSZdZtMrlnSnnQtjpwFwpvFJwFqpwSbqjjqGRpv
    CLCcWHLhLTzsDPcCWMLGpFJbGFwsbvGwJwjpRv
    zMCTPhmHWzfhQQmndNllNrQg
    dbdBdZrQsrdrGslrrSpLvwHmlTmmwScTHv
    FgnJqLDLWqNnNpppmpCSSmCJTw
    NNhhnRNfzMhgnMDFfGdGLbBVVdQRtPVZGt
    BBQJNTTzTcfRhtjhffqDDWCC
    vZnsLsVLSvPwPFFnwPlSPgZWqGjChgWCCWWCMCgGMh
    srLLnLmlPwrrPwmwwvlRqzQRJBmppQTTQpTdBN
    PLDpZGpWbNGWLDfQmsQDwwsmhm
    vTzMMbgCfgHQsmQt
    VMlRznlzVnTcFzbMcrpJcNrJdjdpZrLdcZ
    SftvFcDSvDHsFtctMSvbdjbpqpRRpRTJrMdrrb
    QzQZWZnQgQZwBBwsJdqPjdjrnTpJjs
    NGmwmgszhZwwGGgZGmggWLVSVHlNVVtDcDltFVVVlVHt
    WCfFBfBHHjHHjgHBjJFVcVRwQMbVrRhrJbRRJM
    sZMsDqzZPRrRrVswdc
    vvTzDzpDTvpDvZPvSnNZZlSHMCHjjFtWmlttlCjmCF
    hJZwhrvhBJRrPQPwRRZLllgLqfcqpTggpcTWMTff
    HHDzMztbVgTzNpgf
    nGbmtjDMFjDjCHbbbHHHdBQQPBrZvJQRwvwRPZQJGs
    NNSrMSHRqWpWNNrNMvLffTBBDmsvcmcJLM
    lwPPhcddcGPlBDTDlmDvJJsv
    bZhzCdPGGFzVVPwVwbNtcqHrpnpZptSZqRrt
    GvvSWhmhWBNcBDNc
    FzlRRTljjRTjRRmZfbflRTlFFrrMrcBcDVqBVsNDDJsMFr
    bttRfzfRHzjlmlnCbTtzbRShgwHGGvppLdpvwLLGLLhd
    MHGMWdBFFNsFFHpWSFddMmqVmVBggmlVfbVffjgZml
    hcJsTTscvsLDzDJmqVgfqbqnbmfJbJ
    PvRTzsPwLcwCprSdwdNW
    qfJnJdLpJzrcqCrCzcGfpRSSVBPRSjSSllTNRBdTRS
    DbsbtggsbbsghhgvnWWSlVjPSjmmPBtjPNlTmS
    vHHHHDHvZHQvWbWsZDgWhDwWzpfGfzfcpFJzczwFJrfffnGC
    sQvsRQsFZvfpGhjhQqjpZvjGJHgngPBNHnCBJBCmSBmBNG
    HTHwbtdTDDnCTPTT
    zwMlVdzbzLzMWvQZRQZfZZlHsR
    QhzWwRBPHgFrWWrH
    SDgJCCDCsVpMMqTtFpfpqG
    gJNCCddSZNSlljQzPPNBzR
    dLzVVjfLGCCdRPrdmBtwWttr
    NnbNsbTHJnbHbSHlNQsNtwrJRwBMMBhrPJWZRRtM
    QSslpFvpSSsQPFCDqqgzcjCj
    fcpGshsfNcNZsmRjNqCtnFgbCgHrrggmrn
    QvzBlBBQBdJTBzBwVVMgbrwwLFtLtgLFHCHrbF
    dlQQMBSSTPZfPcfssZNC
    gNGVMzVpVVTdPDWdRdNT
    BfjbnCBjBzffHrbrzBDddQWTZZQTTJTQTHHS
    zrFncfBjcjnrrlCLwFgpmvFmwGmVLh
    MbngccTfWgbWcTTzZghmLshhLRttpthRDLtf
    CdFdJHCJjBvBSCNCNJBjjdjpsPDwDtwvptRPmLmzRwhhLR
    qJSCCBFHQBFFldrVZZbggnGTzcZQ
    lPrpppllcwwpHprppNdfLbQJnWdLJnncdN
    tSjjjSSDGgghRbbSTfTbTFTLQn
    jCBgDMbBMGghZzCZmmlrrpwp
    FhCDFvvPwCjcLhDjhnvjnsdfZTlflQlflLsppdQfld
    zPNSmmHrSSHWBNSMMVGzfGfZTZQZzdpdRGZR
    WSNVVMMVtHSVbMNWBHqmwcvhcgwgvwtPvgtPDjtw
    jSSSjzZMmgSzzwmZBtHcHmtNdncHtnpNcn
    VsLsRsJJsTfRVfLRLJlfLlWqNbDcddncvpvbdvcnpqdpdtHq
    GQQTsJGGJLlRGJFWffWLhgZwrZBZFZrhtBjrjjZw
    ZfzJPvPnLvRJRfZLDfjfrBcqrgsgDBrcrGgslsms
    SNhpqSNhpVTNQSMNgWmrlccVGBBmwrBw
    QHHFhhNdTNHHfZPFqtqPRtjq
    PMZSPSZZGMspsLhLRqRVzfGjvF
    tcwwgcgbcbCrtbbtmQQcCqRffFLhRgqjFjRfhFqhqz
    CLQtcbcmwmbdrbBrlrCwQTsTPsPsZNBPNWJpZWpTss
    lMTrcHrhChWnRzJrznnr
    DDJbPwjLJpfBQjPVBpbsGVGVWnZnsqnZsnzsqZ
    wJQLbpPJDLfgPbDNCHNlghNCMhcNcl
    tlVZhlVWtnBltVtssZBBbPbcpdPwbPWfvcbLvbbb
    NwCTFNFDNdSNPpLpfN
    CGwRjwDjzFFGRGjjFRjlBtZqMzVVtVqhMZMBZn
    HhFdMFHhgrdjcZtZjr
    zvvQQvzwzDMjZTjtcrTDtt
    BwMwSvQSVlzQlMQzwzNgGHPGGHFCCCgGhsHLCS
    zMVtBhhVhhDhtzBtMTTfDrPbmRRmPbQmrQbNQGRQtR
    vLlJHgnLpDvHHvHvmPbSQbQRGmJmPRrb
    pspwHClCwqplsHqDsMMTFWsWfjzszf
    CmmjLwWSWGCHCjwSmStJBgQcccBhwgQtgthQ
    WZVFTpqWsMsZpFddzszbVzJQBnRtrQthchdBgtgRtdrc
    bbsMTWsMVsZqNZMpqWDqbMsCjvlCfjGCPlLLPLCmSCfCLN
    VzsjjVGhpjJrJHCppprt
    WtMnqtWdSQDtMRSnLNHHwHwQvrJrJCPN
    tTtWSScTddBqdRMmlsbFBfhVBhfjjF
    gLMWzdTgLFQHdlMgMRwcwhqqvPcPhVFRDF
    tBnGrSCZNZCrtGBsSNGtBPhcgfchqqDPwVPRvNRqwN
    CmrgstjZngtBzbjJlQWWHjjM
    qttwGWHtVPzJJPqbmb
    NrRvfTTghNrpLrrpLTrNrRrhvJmzmzlbbVVbdbdZlDdvzMPb
    rfcprNcfgpLrVNnnCcnnscstFGCF
    ZZhTfggZsbshGrfshMrNMCSRMMWqCqMNRq
    TVTJPDTFccqMCcJw
    BBLBmLTLDHFvsQpfgnZhbQvG
    ffSrFvVVmVCQSfVDFzDvDDmmnGWCRqGRWNNqlttnRsNtGnWW
    PgZQgPJJpTpTHRGtNRGWqZMWWR
    wdTdwgbPJTJgTgLSFBbrQvSrFrVS
    ppssshsscCVCHhVWVpznnQRBnZnBbzczFPRS
    dqqfJGWttfWGlwwPSbFbZnRFPFtFZS
    wGwdGdddLfGgMTJfwLMlJMpTCChjHhjTHpjjhmsDHmHW
    PZQBhRPQBQrWHFHqHFHCqh
    STQSvvvppzSVHJJFWjHC
    TTgTvbsbszcNnnvbncvRGPBRtRgQrDPLfftPPR
    dMltttpQhmQVZdmhsdrvNCHvlWbHWvHCWrlr
    PzzLTGpGPDzFBzqFGFqFvHWHvRJbbrbWvCvjJCLv
    BpqTBzpzfGGTTPZtSddtQmVffSst
    bwHbRZldhQQfDWWGDjBf
    CzvgpsNMsvCvFvpszpnMsFgBTDBDWPnPVJJZZDJnfjDTff
    CpcLsFrMZbhRcdmt
    HgjpWlhzpWjhWTQPFdPBRQzTMQ
    JsfwrqLttwJVLGhRQGGPBd
    CttfrqDmDDtCsbZCHjhZHSHNlgcW
    QSdCWlCRhWRdlrlZrDssZsGDbv
    pjPrpjqFNrZNGnBbsNDG
    wjjVHjfLQRCgdLrC
    PjMpRdBdjMSGsjpdprqtwCrNGrrNlthhrG
    WQzDzLZDgzZcqlqqrtJclJnh
    zbWHQHDfDWZHfLZHfffWVZpRSPpdVvBSPMqVMPjdvspS
    TMBJLTJlFHBjFFtMGngpvvpgvQmtNSNngv
    bVhsZswRCbbVZWVfVZwVSpmSQPPvNHwPHmgmSSNN
    dCCVZZcbWVVcCbbfsLrdjFMJdDDHBTMrjr
    vNWcTWnCqNCPPjhhHsQrfgszrTJRQsfRQD
    wLdwMBLFBBQJpszJBqzB
    lLwVmMSmttVMlSNqcbcbSbNcvHbh
    PVfJfDWrPVPPLcPPFWcjPrqlqqQsljRpplqBQpRvSQvs
    NdggMTCChMgdChNmdtTbtmsQSRhQslhlpFRpFwllwQvw
    CdnGzbGbgMGMdTCZZDDJcZFDDnWrPH
    fsshhnfLZSvcVbdcZVJj
    RCCSmDFFpRqHQDgWvbGjgjDdbG
    pFmFtCSBCSMBBLwrPsBPNlNB
    fWWcwbbwbWfGCPgPfvbwgvgcQQqQLsGLJQTZHHrZRsrLqlJs
    VnszmsDBpMFpzNFlrlRLRRHZqRHr
    VpdzDMNzNDjpsdzdnzDcPCwtWCjhbthvfgtgwP
    SPQtSWDLLltQQctHLSBSWHlWgFwhMRsgwggrFJPgdgwwGJhJ
    nCqmfVqfVjTznCMhsGRRRgGFMffw
    mCnNTVzVvjmqNtlDtbttDlBM
    LjctjtppFWmgthgs
    nBrNvzTqlDJlbbZgvmhMZVZb
    JJrnTrrgGDqDPwwSPHPpfjRdPc
    sTQmCmmVqmJHSTjGdMMfMNNvNHvc
    rFbzlLLWWPzwlWrlbwzrWbRvdfFjdjpvjfFNNMccphCvhN
    rrWzrwzPBBBCZTJgZg
    gffvjftWddzZtbvdNvgZLwBBMJLSWMDMDDBRWRmS
    PqPqpqcCnCpVqlClTQQmPMDwPRJJBLLLhS
    VCGqlHmmHsjtHNsZ
    mmMlVllWmhmmBzzLGMWlBmpstptPRRZpPMFJSpRsFRFs
    DgjnndQcNTCCCDNcdSRSdtZPfwfwJSJJ
    gQjCQqQjHNnjDCgHNcZGZLhHzrLVLGzlrGmb
    JfwfJpBgJSMphZqtqDDG
    QcQrssrGCcMCVcMc
    RnljPRnPjWbGRbjnjbvmSzwHfHgwfJHzdLFSwBFW
    sBjbHCBCnjvsJCHBsbvwwJGfRNFFFfFGTcrVFffNHRTP
    zDqdpqMgMtgzthgDtQmzGPTVSTVrVGTFSVFFqNRF
    zDLdphmmLhMhDhQdlzgLLbjCnWswWWlrZJBswCJJZl
    FMNrQFgrVwmrpJMwMTMPflbsHPTtlSbftSjCbC
    zGnGnhnGzDqRLnZLHNHbbbHDlltNSjCl
    nzddcRzzBnRRvRRhvnQFpmpgggJVcVmMQmgN
    CCpMlhwwpJpdBlsdcjvtZDFrtmRqmDrsmv
    gzVPbjSPfSPTTTPnWVSbbvDQZZDZrFWDvFDvmvQQmZ
    LTbLTPgnTzLVPNNGnNTgVNPlGdHHCphMwHMwjMphlpjccl
    rMMrqcrmJqJqmCsTPWWGGPzPlPPrGL
    fnwqwwZwRnVlWWnzWBWlDP
    jvVHvqfpJmhtHJtH
    NLMVQjRNTJCTJtZTJc
    DlGlGHvFHGDgcFCtfhCJFtZc
    DBHGGGSDvGDPHWBGdBbSvgWDNVMjLLRnmNmjPLNPNcRQVnjj
    tsGdTJdJtNllzjGRzm
    HZvvDLLWqbBBMRMRNjVhHRmn
    vZDCvqqgBDZZjbZDrWqBvpdpFpcdpCJcPTSJJtptpP
""".trimIndent()