package br.com.stdiagnosticos.common;

import java.util.concurrent.atomic.AtomicLong;

public final class SequenciadorExame {
    private static final SequenciadorExame INST = new SequenciadorExame();
    private final AtomicLong seq = new AtomicLong(1);

    private SequenciadorExame(){}

    public static SequenciadorExame getInstance(){ return INST; }

    public long proximo(){ return seq.getAndIncrement(); }
}
